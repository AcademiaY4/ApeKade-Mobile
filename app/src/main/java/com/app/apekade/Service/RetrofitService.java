package com.app.apekade.Service;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService() {
        this.retrofit = initRetrofit();
    }

    private Retrofit initRetrofit() {
        // Create a logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create a custom OkHttpClient with extended timeout settings
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Set the connection timeout to 30 seconds
                .readTimeout(30, TimeUnit.SECONDS)    // Set the read timeout to 30 seconds
                .writeTimeout(30, TimeUnit.SECONDS)   // Set the write timeout to 30 seconds
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://apekadeserver.azurewebsites.net")
//                .baseUrl("http://10.0.2.2:5259/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}