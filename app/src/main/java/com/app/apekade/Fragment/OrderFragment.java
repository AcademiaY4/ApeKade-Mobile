package com.app.apekade.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.apekade.Adapters.OrderAdapter;
import com.app.apekade.Model.Order;
import com.app.apekade.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.orders_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create dummy data for the orders
        orderList = new ArrayList<>();
        orderList.add(new Order(488454656, "Product Name", 2, 25.00, "Black", "M", "user123", "Pending"));
        orderList.add(new Order(488454656, "Product Name", 2, 25.00, "Black", "M", "user123", "Pending"));
        orderList.add(new Order(488454656, "Product Name", 2, 25.00, "Black", "M", "user123", "Pending"));

        // Add more orders as needed

        // Set up the adapter
        orderAdapter = new OrderAdapter(orderList, getActivity());
        recyclerView.setAdapter(orderAdapter);

        return view;
    }
}
