package com.app.apekade.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.app.apekade.Activity.AccountDelete;
import com.app.apekade.Activity.Home;
import com.app.apekade.Activity.Login;
import com.app.apekade.Activity.OnBoard;
import com.app.apekade.Model.User;
import com.app.apekade.R;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.UserObjUtil;

public class AccountFragment extends Fragment {
    private CardView cvLogoutUser;
    private User dupUser;
    private CardView cvProceedTodlt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Populate views
        cvLogoutUser = view.findViewById(R.id.cvLogoutUser);
        cvProceedTodlt = view.findViewById(R.id.cvProceedTodlt);

        cvLogoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                clear shared_prefs
                JwtTokenUtil.clearToken(requireActivity());
                UserObjUtil.clearUser(requireActivity());
                startActivity(intent);
            }
        });
//        cvProceedTodlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(requireActivity());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.confirmation_dialog);
//
//                Button btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog); // Replace "btnOk" with the actual ID of your "Ok" button
//                Button btnDltConfirmDialog = dialog.findViewById(R.id.btnDltConfirmDialog);
//
//                btnDltConfirmDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                btnCancelDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Handle the "No" button click
//                        // You can perform actions or dismiss the dialog here
//                        dialog.dismiss(); // Close the dialog, for example
//                    }
//                });
//                dialog.show();
//                dialog.setCanceledOnTouchOutside(false); // Prevent canceling when clicked outside
//
//                Window window = dialog.getWindow();
//                if (window != null) {
//                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    window.setGravity(Gravity.BOTTOM);
//                }
//            }
//        });
        cvProceedTodlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AccountDelete.class);
                startActivity(intent);
            }
        });

        return view;
    }
}