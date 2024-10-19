package com.app.apekade.Service;

import com.app.apekade.Model.Order;
import java.util.List;
import retrofit2.Call;

public interface OrderService {
    Call<List<Order>> getOrders(); // Fetch all orders
    Call<Order> getOrderById(String orderId); // Fetch a single order by ID

    Call<Order> getOrderById(long orderId);

    Call<Order> createOrder(Order newOrder); // Create a new order
    Call<Void> updateOrder(String orderId, Order updatedOrder); // Update an existing order
    Call<Void> deleteOrder(String orderId); // Delete an order by ID

    Call<Order> updateOrder(long orderId, Order order);

    Call<Void> deleteOrder(long orderId);
}
