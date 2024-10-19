package com.app.apekade.Fragment;

/**
 * CartDetailFragment.java
 *
 * Description: This fragment displays the user's shopping cart and allows the user to
 * view their selected products, modify quantities, and proceed to checkout.
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.apekade.Model.CartItem;
import com.app.apekade.R;

public class CartDetailFragment extends Fragment {

    private static final String ARG_CART_ITEM = "cart_item";
    private Button  btnCheckout;
    private ImageButton btnIncrease;
    private ImageButton  btnDecrease;
    private TextView quantityTextView;
    private TextView priceTextView;
    private CartItem cartItem;

    public static CartDetailFragment newInstance(CartItem cartItem) {
        CartDetailFragment fragment = new CartDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CART_ITEM, cartItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_detail, container, false);

        cartItem = (CartItem) getArguments().getSerializable(ARG_CART_ITEM);

        btnCheckout = view.findViewById(R.id.btnCheckout2);
        btnIncrease = view.findViewById(R.id.cbtn_plus);
        btnDecrease = view.findViewById(R.id.cbtn_minus);


        // Initialize TextViews with cart item details
        TextView productIdTextView = view.findViewById(R.id.cproduct_id_value);
        quantityTextView = view.findViewById(R.id.cproduct_quantity_value);
        priceTextView = view.findViewById(R.id.cproduct_price_value);

        productIdTextView.setText(cartItem.getProductId());
        quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        priceTextView.setText(String.valueOf(cartItem.getPrice()));

        // Set listeners for increase and decrease buttons
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(1);
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(-1);
            }
        });


        // Set an onClickListener on the checkout button to navigate to payment
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the PaymentFragment
                PaymentFragment paymentFragment = new PaymentFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, paymentFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void updateQuantity(int change) {
        int currentQuantity = cartItem.getQuantity();
        if (change > 0) {
            currentQuantity++;
        } else if (currentQuantity > 1) { // Prevent quantity from going below 1
            currentQuantity--;
        }
        cartItem.setQuantity(currentQuantity);
        quantityTextView.setText(String.valueOf(currentQuantity));
        priceTextView.setText(String.valueOf(cartItem.getPrice() * currentQuantity)); // Update price
    }

    private void removeFromCart() {
        // Logic to remove item from cart
        // This may involve notifying an adapter or a ViewModel, depending on your architecture
        // For example, you can pop this fragment and notify the activity that the item has been removed
        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(this)
                .commit();
    }
}
