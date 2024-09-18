package com.app.apekade.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.apekade.Fragment.AccountFragment;
import com.app.apekade.Fragment.CartFragment;
import com.app.apekade.Fragment.IndexFragment;
import com.app.apekade.Fragment.OrderFragment;
import com.app.apekade.Fragment.ShopFragment;
import com.app.apekade.R;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    // Define constants for resource IDs
    private static final int PROFILE_ID = R.id.profile;
    private static final int HOME_ID = R.id.homie;
    private static final int ORDERS_ID = R.id.orders;
    private static final int CART_ID = R.id.cart;
    private static final int SHOP_ID = R.id.shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        StatusBarUtil.setStatusBarAppearance(this,true);

        //retrieve
        String apiKey = JwtTokenUtil.getToken(this);
//        Toast.makeText(this,apiKey,Toast.LENGTH_LONG).show();

        replaceFrag(new IndexFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == PROFILE_ID) {
                replaceFrag(new AccountFragment());
            } else if (itemId == HOME_ID) {
                replaceFrag(new IndexFragment());
            } else if (itemId == ORDERS_ID) {
                replaceFrag(new OrderFragment());
            } else if (itemId == CART_ID) {
                replaceFrag(new CartFragment());
            } else if (itemId == SHOP_ID) {
                replaceFrag(new ShopFragment());
            } else {
                return false; // Optionally handle unexpected cases
            }
            return true;
        });
    }
    private void replaceFrag(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", "ds");
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}