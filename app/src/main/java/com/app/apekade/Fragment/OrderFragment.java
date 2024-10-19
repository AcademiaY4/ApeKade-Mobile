package com.app.apekade.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Adapters.OrderAdapter;
import com.app.apekade.Model.Order;
import com.app.apekade.R;
import com.app.apekade.Service.OrderService;
import com.app.apekade.Service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    private OrderService orderService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = view.findViewById(R.id.orders_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList, getActivity());
        recyclerView.setAdapter(orderAdapter);

        orderService = new OrderServiceImpl(); // Initialize the OrderService
        fetchOrders(); // Call the function to fetch orders

        return view;
    }

    private void fetchOrders() {
        orderService.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderList.clear();
                    orderList.addAll(response.body());
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Log.e("OrderFragment", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("OrderFragment", "Failure: " + t.getMessage());
            }
        });
    }
}
