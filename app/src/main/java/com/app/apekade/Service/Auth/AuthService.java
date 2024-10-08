package com.app.apekade.Service.Auth;

import com.app.apekade.Model.Dto.LoginDto;
import com.app.apekade.Model.Dto.LoginResDto;
import com.app.apekade.Model.Dto.ServerStatus;
import com.app.apekade.Model.Response.ApiRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @POST("/api/auth/login")
    Call<ApiRes<LoginResDto>> login(@Body LoginDto loginDto);

    @GET("/api")
    Call<ApiRes<ServerStatus>> serverStatus();

//    @POST("/api/collections/users/records")
//    Call<AuthSignUpRes> createUserAuth(@Body AuthSignUp authSignUp);

//    @GET("/api/collections/users/records")
//    Call<User> getUserDetail(
//            @Query("filter") String email,
//            @Header("Authorization") String authorization
//    );
}
