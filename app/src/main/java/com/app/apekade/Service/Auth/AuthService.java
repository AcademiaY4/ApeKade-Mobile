package com.app.apekade.Service.Auth;

import com.app.apekade.Model.Dto.AuthDto.LoginDto;
import com.app.apekade.Model.Dto.AuthDto.LoginResDto;
import com.app.apekade.Model.Dto.AuthDto.RegisterDto;
import com.app.apekade.Model.Dto.AuthDto.RegisterResDto;
import com.app.apekade.Model.Dto.ServerStatus;
import com.app.apekade.Model.Response.ApiRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService {
    @POST("/api/auth/login")
    Call<ApiRes<LoginResDto>> login(@Body LoginDto loginDto);

    @POST("/api/auth/register")
    Call<ApiRes<RegisterResDto>> register(@Body RegisterDto registerDto);

    @GET("/api/user/deactivate-user/{userId}")
    Call<ApiRes<Object>> deactivateUser(
            @Header("Authorization") String authorization,
            @Path("userId") String userId
    );

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
