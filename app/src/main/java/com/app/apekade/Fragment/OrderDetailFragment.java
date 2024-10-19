/**
 * OrderDetailFragment.java
 *
 * Description: This fragment displays the detailed information of a specific order.
 * It shows the order ID, quantity, price, status, and additional details such as color and size.
 * The fragment is used to present a single order's information in a user-friendly interface.
 *
 * Created by: ChalaniS
 * Date: 2024/10/02
 *
 * License: This file is part of the e-commerce mobile application project.
 */

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

import java.io.Serializable;

public class OrderDetailFragment extends Fragment {

    // Key for the argument used to pass the order object to the fragment
    private static final String ARG_ORDER = "order";

    /**
     * Creates a new instance of OrderDetailFragment with the provided order details.
     * @param order The Order object containing the details to be displayed in this fragment.
     * @return A new instance of OrderDetailFragment.
     */
    public static OrderDetailFragment newInstance(Order order) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, (Serializable) order); // Pass the order object as a serializable argument
        fragment.setArguments(args); // Set the arguments to the fragment
        return fragment;
    }

    /**
     * Called to create and return the view hierarchy associated with this fragment.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        // Retrieve the order from the arguments
        Order order = (Order) getArguments().getSerializable(ARG_ORDER);

        // Initialize TextViews to display order details
        TextView orderIdTextView = view.findViewById(R.id.order_id_detail);
        TextView quantityTextView = view.findViewById(R.id.order_quantity_detail);
        TextView priceTextView = view.findViewById(R.id.order_price_detail);
        TextView statusTextView = view.findViewById(R.id.order_status_detail);
        TextView colorTextView = view.findViewById(R.id.order_color_detail);
        TextView sizeTextView = view.findViewById(R.id.order_size_detail);

        // Set order details to the corresponding TextViews
//        orderIdTextView.setText(String.valueOf(order.getOrderId())); // Set order ID
        quantityTextView.setText(String.valueOf(order.getQuantity())); // Set quantity
        priceTextView.setText(String.valueOf(order.getPrice())); // Set price
        statusTextView.setText(order.getStatus()); // Set status
        colorTextView.setText(order.getColor()); // Set color
        sizeTextView.setText(order.getSize()); // Set size

        // Return the created view for rendering
        return view;
    }
}
