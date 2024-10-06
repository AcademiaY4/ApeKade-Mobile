package com.app.apekade.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.apekade.Model.CartItem;
import com.app.apekade.R;

public class CartDetailFragment extends Fragment {

    private static final String ARG_CART_ITEM = "cart_item";

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

        // Retrieve the cart item from arguments
        CartItem cartItem = (CartItem) getArguments().getSerializable(ARG_CART_ITEM);

        // Initialize TextViews with cart item details
        TextView productIdTextView = view.findViewById(R.id.cproduct_id_value);
        TextView quantityTextView = view.findViewById(R.id.cproduct_quantity_value);
        TextView priceTextView = view.findViewById(R.id.cproduct_price_value);
//        ImageView productImageView = view.findViewById(R.id.image_product); // Assuming you have an ImageView for the product image

        // Set cart item details to TextViews
        productIdTextView.setText(cartItem.getProductId());
        quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        priceTextView.setText(String.valueOf(cartItem.getPrice()));

//        // Load the product image using Glide
//        Glide.with(view.getContext())
//                .load(cartItem.getImageUrl()) // Assuming CartItem has getImageUrl() method
//                .into(productImageView);

        return view;
    }
}
