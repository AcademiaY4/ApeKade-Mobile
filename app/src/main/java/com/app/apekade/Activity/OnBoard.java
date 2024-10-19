package com.app.apekade.Activity;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.apekade.R;
import com.app.apekade.Utils.JwtTokenUtil;

public class OnBoard extends AppCompatActivity {
    private CardView cvGetStartedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_board);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, 50);
            return insets;
        });
        cvGetStartedBtn = findViewById(R.id.cvGetStartedBtn);
        cvGetStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        // Check if user is already logged in
        if (JwtTokenUtil.isTokenValid(this)) {
            // Navigate to Home activity if the token is valid
            Intent intent = new Intent(OnBoard.this, Home.class);
            startActivity(intent);
            finish();  // Finish the OnBoard activity so it won't be accessible if the user is logged in
        }
    }
}