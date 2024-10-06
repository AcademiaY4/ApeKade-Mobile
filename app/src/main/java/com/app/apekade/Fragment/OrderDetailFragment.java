package com.app.apekade.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.apekade.Model.Order;
import com.app.apekade.R;

public class OrderDetailFragment extends Fragment {

    private static final String ARG_ORDER = "order";

    public static OrderDetailFragment newInstance(Order order) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        // Retrieve the order from arguments
        Order order = (Order) getArguments().getSerializable(ARG_ORDER);

        // Initialize TextViews with order details
        TextView orderIdTextView = view.findViewById(R.id.order_id_detail);
        TextView quantityTextView = view.findViewById(R.id.order_quantity_detail);
        TextView priceTextView = view.findViewById(R.id.order_price_detail);
        TextView statusTextView = view.findViewById(R.id.order_status_detail);
        TextView colorTextView = view.findViewById(R.id.order_color_detail);
        TextView sizeTextView = view.findViewById(R.id.order_size_detail);

        // Set order details to TextViews
        orderIdTextView.setText(String.valueOf(order.getOrderId()));
        quantityTextView.setText(String.valueOf(order.getQuantity()));
        priceTextView.setText(String.valueOf(order.getPrice()));
        statusTextView.setText(order.getStatus());
        colorTextView.setText(order.getColor());
        sizeTextView.setText(order.getSize());

        return view;
    }
}
