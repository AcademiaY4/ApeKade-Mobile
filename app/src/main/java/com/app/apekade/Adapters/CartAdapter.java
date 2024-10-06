package com.app.apekade.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.bind(cartItem);
        holder.itemView.setOnClickListener(v -> {
            // Navigate to CartDetailFragment using replace to prevent fragment overlap
            CartDetailFragment detailFragment = CartDetailFragment.newInstance(cartItem);
            ((Home) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView productIdValue;
        private final TextView quantityValue;
        private final TextView priceValue;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productIdValue = itemView.findViewById(R.id.cproduct_id_value);
            quantityValue = itemView.findViewById(R.id.cproduct_quantity_value);
            priceValue = itemView.findViewById(R.id.cproduct_price_value);
        }

        public void bind(CartItem cartItem) {
            if (productIdValue != null) {
                productIdValue.setText(cartItem.getProductId() != null ? String.valueOf(cartItem.getProductId()) : "N/A");
            }
            if (quantityValue != null) {
                quantityValue.setText(cartItem.getQuantity() > 0 ? String.valueOf(cartItem.getQuantity()) : "0");
            }
            if (priceValue != null) {
                priceValue.setText(cartItem.getPrice() >= 0 ? String.valueOf(cartItem.getPrice()) : "N/A");
            }
        }
    }
}
