package com.app.apekade.Adapters;

/**
 * CartAdapter.java
 *
 * Description: Adapter class for managing and displaying cart items in a RecyclerView.
 * This adapter is responsible for binding cart data to the UI, handling user interactions,
 * and updating the quantity of cart items.
 *
 * Created by: ChalaniS
 * Date: 2024/10/02
 *
 * Modifications:
 * - Modification 1: connected with UI
 *
 * License: This file is part of the e-commerce mobile application project.
 */


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

    /**
     * Constructor for CartAdapter.
     * @param cartItems List of cart items to be displayed in the RecyclerView.
     * @param context Context of the activity or fragment that uses this adapter.
     */
    public CartAdapter(List<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to represent an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new CartViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single cart item
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the cart item for the given position
        CartItem cartItem = cartItems.get(position);
        holder.bind(cartItem); // Bind data to the view holder

        // Set click listener to navigate to CartDetailFragment when item is clicked
        holder.itemView.setOnClickListener(v -> {
            CartDetailFragment detailFragment = CartDetailFragment.newInstance(cartItem);
            ((Home) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Set click listener for the minus button to decrease item quantity
        holder.btnMinus.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1); // Decrease quantity
                holder.cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
                holder.cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price
            }
        });

        // Set click listener for the plus button to increase item quantity
        holder.btnPlus.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1); // Increase quantity
            holder.cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
            holder.cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The size of the cart items list.
     */
    @Override
    public int getItemCount() {
        return cartItems.size(); // Return the size of the cart items list
    }

    /**
     * ViewHolder class that describes the item view and metadata about its place within the RecyclerView.
     */
    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartQuantityValue;
        private final TextView cartPriceValue;
        private final ImageButton btnMinus;
        private final ImageButton btnPlus;

        /**
         * Constructor for CartViewHolder.
         * @param itemView The view of a single item in the cart.
         */
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartQuantityValue = itemView.findViewById(R.id.cart_quantity_value);
            cartPriceValue = itemView.findViewById(R.id.cart_price_value);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
        }

        /**
         * Binds the data from a CartItem to the UI elements of the view holder.
         * @param cartItem The CartItem object containing data to be displayed.
         */
        public void bind(CartItem cartItem) {
            // Set quantity and price values for the cart item
            cartQuantityValue.setText(String.valueOf(cartItem.getQuantity()));
            cartPriceValue.setText("$" + (cartItem.getPrice() * cartItem.getQuantity())); // Update price dynamically
        }
    }
}
