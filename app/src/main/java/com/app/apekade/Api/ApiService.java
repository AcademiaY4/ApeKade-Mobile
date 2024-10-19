package com.app.apekade.Api;

import com.app.apekade.Model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @GET("/api/orders")
    Call<List<Order>> getOrders();

    @GET("/api/orders/{id}")
    Call<Order> getOrderById(@Path("id") long orderId);

    @POST("/api/orders")
    Call<Order> createOrder(@Body Order order);

    @PUT("/api/orders/{id}")
    Call<Order> updateOrder(@Path("id") long orderId, @Body Order order);

    @DELETE("/api/orders/{id}")
    Call<Void> deleteOrder(@Path("id") long orderId);
}
