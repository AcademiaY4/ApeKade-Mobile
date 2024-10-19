package com.app.apekade.Service;

import com.app.apekade.Api.ApiService;
import com.app.apekade.Api.RetrofitClient;
import com.app.apekade.Model.Order;

import java.util.List;

import retrofit2.Call;

public class OrderServiceImpl implements OrderService {
    private ApiService apiService;

    public OrderServiceImpl() {
        this.apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public Call<List<Order>> getOrders() {
        return apiService.getOrders();
    }

    @Override
    public Call<Order> getOrderById(String orderId) {
        return null;
    }

    @Override
    public Call<Order> getOrderById(long orderId) {
        return apiService.getOrderById(orderId);
    }

    @Override
    public Call<Order> createOrder(Order order) {
        return apiService.createOrder(order);
    }

    @Override
    public Call<Void> updateOrder(String orderId, Order updatedOrder) {
        return null;
    }

    @Override
    public Call<Void> deleteOrder(String orderId) {
        return null;
    }

    @Override
    public Call<Order> updateOrder(long orderId, Order order) {
        return apiService.updateOrder(orderId, order);
    }

    @Override
    public Call<Void> deleteOrder(long orderId) {
        return apiService.deleteOrder(orderId);
    }
}
