package com.app.apekade.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Adapters.CustomerReviewAdapter;

import com.app.apekade.Components.ProgressLoader.ProgressLoader;
import com.app.apekade.Model.Dto.AddVendorRatingDto;
import com.app.apekade.Model.Rating;
import com.app.apekade.Model.Dto.GetVendorResDto;
import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.R;

import com.app.apekade.Service.RetrofitService;
import com.app.apekade.Service.Vendor.VendorService;
import com.app.apekade.Utils.JwtTokenUtil;
import com.app.apekade.Utils.StatusBarUtil;
import com.app.apekade.Utils.UserObjUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SellerInformationActivity extends AppCompatActivity {

    private TextView sellerName, sellerDescription, overallRating, itemQualityRating, communicationRating, shippingSpeedRating, positiveReviews;
    private Button rateReviewButton;
    private RecyclerView recyclerView;
    private CustomerReviewAdapter reviewAdapter;
    private ProgressLoader progressLoader;
    private VendorService apiService;
    private String vendorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seller_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // handle status bar icon colors
        StatusBarUtil.setStatusBarAppearance(this,true);

        vendorId = getIntent().getStringExtra("VENDOR_ID");
        if (vendorId == null) {
            Toast.makeText(this, "Vendor ID is missing", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initViews();
        initRetrofit();
        initRecyclerView();
        fetchSellerInfo();

        rateReviewButton.setOnClickListener(v -> showRatingDialog());
    }

    private void initViews() {
        sellerName = findViewById(R.id.tvSellerName);
        sellerDescription = findViewById(R.id.tvSellerDescription);
        overallRating = findViewById(R.id.tvAvgRating);
        itemQualityRating = findViewById(R.id.tvQualityRating);
        communicationRating = findViewById(R.id.tvComRating);
        shippingSpeedRating = findViewById(R.id.tvShipRating);
        positiveReviews = findViewById(R.id.tvAvgRatingPercent);
        rateReviewButton = findViewById(R.id.btnRateReview);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initRetrofit() {
        RetrofitService retrofitService = new RetrofitService();
        apiService = retrofitService.getRetrofit().create(VendorService.class);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new CustomerReviewAdapter(new ArrayList<>());
        recyclerView.setAdapter(reviewAdapter);
    }

    private void fetchSellerInfo() {
        progressLoader = new ProgressLoader(this, "Fetching Seller Info", "Please Wait");
        progressLoader.startProgressLoader();

        String token = JwtTokenUtil.getToken(this);
        Call<ApiRes<GetVendorResDto>> call = apiService.getVendorById("Bearer " + token, vendorId);
        call.enqueue(new Callback<ApiRes<GetVendorResDto>>() {
            @Override
            public void onResponse(Call<ApiRes<GetVendorResDto>> call, Response<ApiRes<GetVendorResDto>> response) {
                progressLoader.dismissProgressLoader();
                if (response.isSuccessful() && response.body() != null) {
                    ApiRes<GetVendorResDto> apiResponse = response.body();
                    if (apiResponse.getStatus()) {
                        updateUI(apiResponse.getData());
                    } else {
                        String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Failed to fetch seller info";
                        Toast.makeText(SellerInformationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SellerInformationActivity.this, "Error fetching seller info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiRes<GetVendorResDto>> call, Throwable t) {
                progressLoader.dismissProgressLoader();
                Log.e("SellerInformationActivity", "Request Failed", t);
                Toast.makeText(SellerInformationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(GetVendorResDto vendor) {
        sellerName.setText(vendor.getShopName());
        sellerDescription.setText(vendor.getShopDescription());

        float avgItemQuality = 0f, avgCommunication = 0f, avgShippingSpeed = 0f;
        int totalRatings = vendor.getVendorRatings().size();

        for (Rating rating : vendor.getVendorRatings()) {
            avgItemQuality += rating.getItemQualityRating();
            avgCommunication += rating.getCommunicationRating();
            avgShippingSpeed += rating.getShippingSpeedRating();
        }

        if (totalRatings > 0) {
            avgItemQuality /= totalRatings;
            avgCommunication /= totalRatings;
            avgShippingSpeed /= totalRatings;
        }

        float overallAvg = (avgItemQuality + avgCommunication + avgShippingSpeed) / 3;

        overallRating.setText(String.format("%.1f", overallAvg));
        itemQualityRating.setText(String.format("%.1f", avgItemQuality));
        communicationRating.setText(String.format("%.1f", avgCommunication));
        shippingSpeedRating.setText(String.format("%.1f", avgShippingSpeed));

        // Calculate positive reviews (assuming ratings >= 4 are positive)
        int positiveCount = 0;
        for (Rating rating : vendor.getVendorRatings()) {
            float avgRating = (rating.getItemQualityRating() + rating.getCommunicationRating() + rating.getShippingSpeedRating()) / 3;
            if (avgRating >= 4) {
                positiveCount++;
            }
        }
        float positivePercentage = totalRatings > 0 ? (float) positiveCount / totalRatings * 100 : 0;
        positiveReviews.setText(String.format("%.1f", positivePercentage));

        // Update the RecyclerView with the new ratings
        reviewAdapter.updateReviews(vendor.getVendorRatings());
    }

    private void showRatingDialog() {
        final Dialog dialog = new Dialog(this, R.style.RoundedCornersDialog);
        dialog.setContentView(R.layout.dialog_rate_review);

        RatingBar ratingItemQuality = dialog.findViewById(R.id.ratingItemQuality);
        RatingBar ratingCommunication = dialog.findViewById(R.id.ratingCommunication);
        RatingBar ratingShippingSpeed = dialog.findViewById(R.id.ratingShippingSpeed);
        EditText editTextComment = dialog.findViewById(R.id.editTextComment);
        Button buttonSubmitFeedback = dialog.findViewById(R.id.btnSubmitFeedback);

        buttonSubmitFeedback.setOnClickListener(v -> {
            submitFeedback(
                    ratingItemQuality.getRating(),
                    ratingCommunication.getRating(),
                    ratingShippingSpeed.getRating(),
                    editTextComment.getText().toString()
            );
            dialog.dismiss();
        });

        dialog.show();
    }

    private void submitFeedback(float itemQuality, float communication, float shippingSpeed, String comment) {
        progressLoader = new ProgressLoader(this, "Submitting Feedback", "Please Wait");
        progressLoader.startProgressLoader();

        String token = JwtTokenUtil.getToken(this);

        AddVendorRatingDto ratingDto = new AddVendorRatingDto(
                vendorId,
                itemQuality,
                communication,
                shippingSpeed,
                comment
        );

        Call<ApiRes<Void>> call = apiService.addVendorRating("Bearer " + token, ratingDto);
        call.enqueue(new Callback<ApiRes<Void>>() {
            @Override
            public void onResponse(Call<ApiRes<Void>> call, Response<ApiRes<Void>> response) {
                progressLoader.dismissProgressLoader();
                if (response.isSuccessful() && response.body() != null) {
                    ApiRes<Void> apiResponse = response.body();
                    if (apiResponse.getStatus()) {
                        Toast.makeText(SellerInformationActivity.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                        fetchSellerInfo(); // Refresh seller info
                    } else {
                        String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Failed to submit feedback";
                        Toast.makeText(SellerInformationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SellerInformationActivity.this, "Error submitting feedback", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiRes<Void>> call, Throwable t) {
                progressLoader.dismissProgressLoader();
                Log.e("SellerInfoActivity", "Request Failed", t);
                Toast.makeText(SellerInformationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
