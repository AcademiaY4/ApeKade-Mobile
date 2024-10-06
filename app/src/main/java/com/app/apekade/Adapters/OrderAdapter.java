package com.app.apekade.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Activity.Home;
import com.app.apekade.Model.Order;
import com.app.apekade.Fragment.OrderDetailFragment;
import com.app.apekade.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<Order> orders;
    private final Context context;

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
        holder.itemView.setOnClickListener(v -> {
            // Navigate to OrderDetailFragment without fixing to the order details page
            OrderDetailFragment detailFragment = OrderDetailFragment.newInstance(order);
            ((Home) context).getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, detailFragment) // Use add() instead of replace()
                    .addToBackStack(null) // Adds this transaction to the back stack
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderIdLabel;
        private final TextView orderIdValue;
        private final TextView orderQuantityLabel;
        private final TextView orderQuantityValue;
        private final TextView orderPriceValue;
        private final TextView orderStatusLabel;
        private final TextView orderStatusValue;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdLabel = itemView.findViewById(R.id.order_id_label);
            orderIdValue = itemView.findViewById(R.id.order_id_value);
            orderQuantityLabel = itemView.findViewById(R.id.quantity_label);
            orderQuantityValue = itemView.findViewById(R.id.order_quantity_value);
            orderPriceValue = itemView.findViewById(R.id.order_price_value);
            orderStatusLabel = itemView.findViewById(R.id.order_status_label);
            orderStatusValue = itemView.findViewById(R.id.order_status_value);
        }

        public void bind(Order order) {
            orderIdValue.setText(String.valueOf(order.getOrderId())); // Set the order ID
            orderQuantityValue.setText(String.valueOf(order.getQuantity()));
            orderPriceValue.setText(String.valueOf(order.getPrice()));
            orderStatusValue.setText(order.getStatus());
        }
    }
}
