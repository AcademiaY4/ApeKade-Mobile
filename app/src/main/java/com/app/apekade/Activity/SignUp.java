package com.app.apekade.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

import com.app.apekade.Components.ProgressLoader.ProgressLoader;
import com.app.apekade.Model.Dto.AuthDto.RegisterDto;
import com.app.apekade.Model.Dto.AuthDto.RegisterResDto;
import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.R;
import com.app.apekade.Service.Auth.AuthService;
import com.app.apekade.Service.RetrofitService;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.StatusBarUtil;
import com.app.apekade.Utils.UserObjUtil;
import com.app.apekade.Utils.Validation.Schema.SignUpForm;
import com.app.apekade.Utils.Validation.Validator.SignUpValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private EditText etNameFirstName, etNameLastName, etTelAge, etTelCity, etTelephone, etEmailSignUp, etPasswordSignUp;
    private Spinner spinnerDistrict, spinnerProvince;
    private CardView cvSignUpBtn;
    private TextView tvredirectToSignIn;
    private ProgressLoader progressLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        StatusBarUtil.setStatusBarAppearance(this,true);

        // Initialize EditTexts
        etNameFirstName = findViewById(R.id.etNameFirstName);
        etNameLastName = findViewById(R.id.etNameLastName);
        etTelAge = findViewById(R.id.etTelAge);
        etTelCity = findViewById(R.id.etTelCity);
        etTelephone = findViewById(R.id.etTelephone);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);

        // Initialize Spinners
        spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerProvince = findViewById(R.id.spinnerProvince);

        // Initialize Buttons and TextView
        cvSignUpBtn = findViewById(R.id.cvSignUpBtn);
        tvredirectToSignIn = findViewById(R.id.tvredirectToSignIn);

        // Set Click Listener for Sign Up button
        cvSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(
                        etNameFirstName.getText().toString(),
                        etNameLastName.getText().toString(),
                        etTelAge.getText().toString(),
                        etTelCity.getText().toString(),
                        etTelephone.getText().toString(),
                        etEmailSignUp.getText().toString(),
                        etPasswordSignUp.getText().toString(),
                        spinnerDistrict.getSelectedItem().toString(),
                        spinnerProvince.getSelectedItem().toString()
                );
            }
        });

        // Set Click Listener for Redirect to SignIn button
        tvredirectToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    private void register(String firstName, String lastName, String age, String city, String telephone, String email, String password, String district, String province) {
        SignUpForm signUpForm = new SignUpForm(firstName, lastName, email, password,telephone, Integer.parseInt(age), district, province,city);
        SignUpValidator validator = new SignUpValidator(signUpForm);

        // Pass the EditText fields to the validator for error setting
        if (validator.isValid(firstName, lastName, email, password,telephone, Integer.parseInt(age), district, province,city,
                etNameFirstName, etNameLastName, etEmailSignUp, etPasswordSignUp, etTelephone,etTelAge,etTelCity)) {

            progressLoader = new ProgressLoader(this, "Creating Account", "Please Wait");
            progressLoader.startProgressLoader();

            RetrofitService retrofitService = new RetrofitService();
            AuthService authService = retrofitService.getRetrofit().create(AuthService.class);

            RegisterDto registerDto = new RegisterDto(firstName, lastName,email, password,"BUYER", "+94"+telephone,Integer.parseInt(age), district, province,city);
            Call<ApiRes<RegisterResDto>> call = authService.register(registerDto);
            call.enqueue(new Callback<ApiRes<RegisterResDto>>() {
                @Override
                public void onResponse(Call<ApiRes<RegisterResDto>> call, Response<ApiRes<RegisterResDto>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiRes<RegisterResDto> apiResponse = response.body();
                        if (apiResponse.getStatus()) {
                            RegisterResDto registerResDto = apiResponse.getData();

                            // Store user and token
                            JwtTokenUtil.saveToken(SignUp.this, registerResDto.getAccessToken());
                            UserObjUtil.saveUser(SignUp.this, registerResDto.getUser());

                            // Start Home Activity
                            progressLoader.dismissProgressLoader();
                            Intent intent = new Intent(SignUp.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Unknown error occurred";
                            Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                            progressLoader.dismissProgressLoader();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        progressLoader.dismissProgressLoader();
                    }
                }

                @Override
                public void onFailure(Call<ApiRes<RegisterResDto>> call, Throwable t) {
                    Log.e("SignUpActivity", "Request Failed", t);
                    Toast.makeText(SignUp.this, "Server Error", Toast.LENGTH_SHORT).show();
                    progressLoader.dismissProgressLoader();
                }
            });
        }
    }
}