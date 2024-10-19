package com.app.apekade.Fragment;

/**
 * CartFragment.java
 *
 * Description: This fragment represents the shopping cart view in the e-commerce application.
 * It displays the list of items added to the cart, allows users to modify quantities, and proceed
 * to the checkout process. The fragment also handles interactions such as viewing item details
 * and navigating to the payment screen.
 *
 * Created by: ChalaniS
 * Date: 2024/10/02
 *
 * Modifications:
 * - Modification 1: Add sample items
 *
 * License: This file is part of the e-commerce mobile application project.
 */


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.apekade.Adapters.CartAdapter;
import com.app.apekade.Model.CartItem;
import com.app.apekade.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private Button btnCheckout;  // Declare checkout button

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.carts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the checkout button
        btnCheckout = view.findViewById(R.id.btnCheckout);

        // Create a sample cart item list
        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("1", "P001", "https://example.com/product_image_1.jpg", 1, 15.00));
        cartItemList.add(new CartItem("2", "P002", "https://example.com/product_image_2.jpg", 2, 20.00));
        cartItemList.add(new CartItem("3", "P003", "https://example.com/product_image_3.jpg", 1, 30.00));

        // Set up the adapter
        cartAdapter = new CartAdapter(cartItemList, getActivity());
        recyclerView.setAdapter(cartAdapter);

        // Set an onClickListener on the checkout button to navigate to payment
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the PaymentFragment
                PaymentFragment paymentFragment = new PaymentFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, paymentFragment) // Assuming 'fragment_container' is the ID of your fragment container
                        .addToBackStack(null) // Optional: adds this transaction to the back stack
                        .commit();
            }
        });

        return view;
    }
}
