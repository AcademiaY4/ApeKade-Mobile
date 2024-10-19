package com.app.apekade.Service.Vendor;

import com.app.apekade.Model.Dto.AddVendorRatingDto;
import com.app.apekade.Model.Dto.GetVendorResDto;
import com.app.apekade.Model.Response.ApiRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VendorService {

    @GET("/api/buyer/get-vendor/{vendorId}")
    Call<ApiRes<GetVendorResDto>> getVendorById(@Header("Authorization") String token, @Path("vendorId") String vendorId);

    @POST("/api/buyer/add-vendor-rating")
    Call<ApiRes<Void>> addVendorRating(@Header("Authorization") String token, @Body AddVendorRatingDto addVendorRatingDto);
}
