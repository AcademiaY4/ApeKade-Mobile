package com.app.apekade.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.apekade.R;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.StatusBarUtil;

public class Login extends AppCompatActivity {

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

        // dummy token store
        JwtTokenUtil.saveToken(this,"abcd");

        // Start Home Activity after storing token
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }
}