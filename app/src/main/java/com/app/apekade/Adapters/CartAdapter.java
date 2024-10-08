package com.app.apekade.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Fragment.CartDetailFragment;
import com.app.apekade.Model.CartItem;
import com.app.apekade.Activity.Home;
import com.app.apekade.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<CartItem> cartItems;
    private final Context context;

    public CartAdapter(List<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.bind(cartItem); // Bind data to the view holder

        // Set click listener to navigate to CartDetailFragment
        holder.itemView.setOnClickListener(v -> {
            CartDetailFragment detailFragment = CartDetailFragment.newInstance(cartItem);
            ((Home) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Decrease quantity
        holder.btnMinus.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1); // Decrease quantity
                holder.cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
                holder.cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price
            }
        });

        // Increase quantity
        holder.btnPlus.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1); // Increase quantity
            holder.cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
            holder.cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size(); // Return the size of the cart items list
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartQuantityValue;
        private final TextView cartPriceValue;
        private final ImageButton btnMinus;
        private final ImageButton btnPlus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartQuantityValue = itemView.findViewById(R.id.cart_quantity_value);
            cartPriceValue = itemView.findViewById(R.id.cart_price_value);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
        }

        public void bind(CartItem cartItem) {
            // Bind data from CartItem to UI elements
            cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
            cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price dynamically
        }
    }
}
