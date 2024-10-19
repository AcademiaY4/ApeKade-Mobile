package com.app.apekade.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

import com.app.apekade.Components.ProgressLoader.ProgressLoader;
import com.app.apekade.Model.Dto.AuthDto.LoginDto;
import com.app.apekade.Model.Dto.AuthDto.LoginResDto;
import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.R;
import com.app.apekade.Service.Auth.AuthService;
import com.app.apekade.Service.RetrofitService;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.StatusBarUtil;
import com.app.apekade.Utils.UserObjUtil;
import com.app.apekade.Utils.Validation.Schema.SignInForm;
import com.app.apekade.Utils.Validation.Validator.SignInValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText etSignInEmail;
    private EditText etSignInPassword;
    private CardView cvSignInBtn;
    private TextView tvredirectToSignUp;
    private ProgressLoader progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // handle status bar icon colors
        StatusBarUtil.setStatusBarAppearance(this,true);

        etSignInPassword = findViewById(R.id.etSignInPassword);
        etSignInEmail = findViewById(R.id.etSignInEmail);
        tvredirectToSignUp = findViewById(R.id.tvredirectToSignUp);
        cvSignInBtn = findViewById(R.id.cvSignInBtn);

        cvSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etSignInEmail.getText().toString(),etSignInPassword.getText().toString());
            }
        });
        tvredirectToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }
    private void login(String email, String password) {
        SignInForm signInForm = new SignInForm(email, password);
        SignInValidator validator = new SignInValidator(signInForm);
        // Pass the EditText fields to the validator for error setting
        if (validator.isValid(email, password, etSignInEmail, etSignInPassword)) {
            progressLoader = new ProgressLoader(this, "Verifying Login", "Please Wait");
            progressLoader.startProgressLoader();

            RetrofitService retrofitService = new RetrofitService();
            AuthService authService = retrofitService.getRetrofit().create(AuthService.class);

            Call<ApiRes<LoginResDto>> call = authService.login(new LoginDto(email, password,"BUYER"));
            call.enqueue(new Callback<ApiRes<LoginResDto>>() {
                @Override
                public void onResponse(Call<ApiRes<LoginResDto>> call, Response<ApiRes<LoginResDto>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiRes<LoginResDto> apiResponse = response.body();
                        if (apiResponse.getStatus()) {
                            LoginResDto loginResDto = apiResponse.getData();

                            // Store user and token
                            JwtTokenUtil.saveToken(Login.this, loginResDto.getAccessToken());
                            UserObjUtil.saveUser(Login.this, loginResDto.getUser());

                            // Start Home Activity
                            progressLoader.dismissProgressLoader();
                            Intent intent = new Intent(Login.this, Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            // Check if the message is null
                            String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Unknown error occurred";
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            progressLoader.dismissProgressLoader();
                        }
                    } else {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        progressLoader.dismissProgressLoader();
                    }
                }
                @Override
                public void onFailure(Call<ApiRes<LoginResDto>> call, Throwable t) {
                    Log.e("LoginActivity", "Request Failed", t);
                    Toast.makeText(Login.this, "Server Error", Toast.LENGTH_SHORT).show();
                    progressLoader.dismissProgressLoader();
                }
            });
        }
    }
}