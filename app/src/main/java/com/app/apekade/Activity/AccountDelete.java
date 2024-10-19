package com.app.apekade.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.apekade.Components.ProgressLoader.ProgressLoader;
import com.app.apekade.Model.Dto.AuthDto.LoginDto;
import com.app.apekade.Model.Dto.AuthDto.LoginResDto;
import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.R;
import com.app.apekade.Service.Auth.AuthService;
import com.app.apekade.Service.RetrofitService;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.UserObjUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDelete extends AppCompatActivity {
    private CardView cvMyActDltProBtn;
    private CardView cvMyActBackBtn;
    private ProgressLoader progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cvMyActDltProBtn = findViewById(R.id.cvMyActDltProBtn);
        cvMyActBackBtn = findViewById(R.id.cvMyActBackBtn);

        cvMyActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cvMyActDltProBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AccountDelete.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.confirmation_dialog);

                Button btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog); // Replace "btnOk" with the actual ID of your "Ok" button
                Button btnDltConfirmDialog = dialog.findViewById(R.id.btnDltConfirmDialog);

                btnDltConfirmDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deactivateUser();
                    }
                });
                btnCancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle the "No" button click
                        // You can perform actions or dismiss the dialog here
                        dialog.dismiss(); // Close the dialog, for example
                    }
                });
                dialog.show();
                dialog.setCanceledOnTouchOutside(false); // Prevent canceling when clicked outside

                Window window = dialog.getWindow();
                if (window != null) {
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setGravity(Gravity.BOTTOM);
                }
            }
        });
    }
    public void deactivateUser(){
        progressLoader = new ProgressLoader(this, "Deactivating Account", "Please Wait");
        progressLoader.startProgressLoader();

        RetrofitService retrofitService = new RetrofitService();
        AuthService authService = retrofitService.getRetrofit().create(AuthService.class);

        String userId = UserObjUtil.getUser(AccountDelete.this).getId();
        String token = "Bearer "+JwtTokenUtil.getToken(AccountDelete.this);

        Call<ApiRes<Object>> call = authService.deactivateUser(token,userId);
        call.enqueue(new Callback<ApiRes<Object>>() {
            @Override
            public void onResponse(Call<ApiRes<Object>> call, Response<ApiRes<Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiRes<Object> apiResponse = response.body();
                    if (apiResponse.getStatus()) {
                        // Start Login Activity
                        progressLoader.dismissProgressLoader();
                        Intent intent = new Intent(AccountDelete.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        // Check if the message is null
                        String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Unknown error occurred";
                        Toast.makeText(AccountDelete.this, message, Toast.LENGTH_SHORT).show();
                        progressLoader.dismissProgressLoader();
                    }
                } else {
                    Toast.makeText(AccountDelete.this, "Api Call Failure", Toast.LENGTH_SHORT).show();
                    progressLoader.dismissProgressLoader();
                }
            }
            @Override
            public void onFailure(Call<ApiRes<Object>> call, Throwable t) {
                Log.e("AccountDelete", "Request Failed", t);
                Toast.makeText(AccountDelete.this, "Server Error", Toast.LENGTH_SHORT).show();
                progressLoader.dismissProgressLoader();
            }
        });
    }
}