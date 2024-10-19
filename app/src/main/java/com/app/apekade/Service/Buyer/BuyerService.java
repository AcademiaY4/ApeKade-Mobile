package com.app.apekade.Service.Buyer;

import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.Model.UserReview;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BuyerService {
    @GET("api/buyer/get-reviews")
    Call<ApiRes<List<UserReview>>> getUserReviews(@Header("Authorization") String token);

    @PUT("api/buyer/update-review/{id}")
    Call<ApiRes<UserReview>> updateReview(@Header("Authorization") String token, @Path("id") String id, @Body UserReview review);
}
