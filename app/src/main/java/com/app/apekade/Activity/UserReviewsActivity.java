package com.app.apekade.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Adapters.UserReviewAdapter;
import com.app.apekade.Components.ProgressLoader.ProgressLoader;
import com.app.apekade.Model.Response.ApiRes;
import com.app.apekade.Model.UserReview;
import com.app.apekade.R;
import com.app.apekade.Service.Buyer.BuyerService;
import com.app.apekade.Service.RetrofitService;
import com.app.apekade.Utils.JwtTokenUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserReviewsActivity extends AppCompatActivity implements UserReviewAdapter.OnReviewClickListener {

    private RecyclerView reviewsRecyclerView;
    private UserReviewAdapter reviewAdapter;
    private List<UserReview> reviewList;
    private BuyerService apiService;
    private ProgressLoader progressLoader;
    private TextView reviewCountTextView;
    private TextView averageRatingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reviews);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        reviewCountTextView = findViewById(R.id.reviewCountTextView);
        averageRatingTextView = findViewById(R.id.averageRatingTextView);
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewList = new ArrayList<>();
        reviewAdapter = new UserReviewAdapter(reviewList, this);

        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewAdapter);

        setupRetrofit();
        fetchUserReviews();
    }

    private void setupRetrofit() {
        RetrofitService retrofitService = new RetrofitService();
        apiService = retrofitService.getRetrofit().create(BuyerService.class);
    }

    private void fetchUserReviews() {
        progressLoader = new ProgressLoader(this, "Fetching Reviews", "Please Wait");
        progressLoader.startProgressLoader();

        String token = JwtTokenUtil.getToken(this);
        Call<ApiRes<List<UserReview>>> call = apiService.getUserReviews("Bearer " + token);
        call.enqueue(new Callback<ApiRes<List<UserReview>>>() {
            @Override
            public void onResponse(Call<ApiRes<List<UserReview>>> call, Response<ApiRes<List<UserReview>>> response) {
                progressLoader.dismissProgressLoader();
                if (response.isSuccessful() && response.body() != null) {
                    ApiRes<List<UserReview>> apiResponse = response.body();
                    if (apiResponse.getStatus()) {
                        reviewList.clear();
                        reviewList.addAll(apiResponse.getData());
                        reviewAdapter.notifyDataSetChanged();
                        updateReviewSummary();
                    } else {
                        String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Failed to fetch reviews";
                        Toast.makeText(UserReviewsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserReviewsActivity.this, "Error fetching reviews", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiRes<List<UserReview>>> call, Throwable t) {
                progressLoader.dismissProgressLoader();
                Log.e("UserReviewsActivity", "Request Failed", t);
                Toast.makeText(UserReviewsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateReviewSummary() {
        int reviewCount = reviewList.size();
        reviewCountTextView.setText("Total Reviews: " + reviewCount);

        if (reviewCount > 0) {
            double averageRating = calculateAverageRating();
            averageRatingTextView.setText(String.format("Average Rating: %.1f", averageRating));
        } else {
            averageRatingTextView.setText("Average Rating: N/A");
        }
    }

    private double calculateAverageRating() {
        if (reviewList.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (UserReview review : reviewList) {
//            sum += review.getRating();
              sum += 1;
        }
        return sum / reviewList.size();
    }

    @Override
    public void onReviewClick(UserReview review) {
        showEditReviewDialog(review);
    }

    private void showEditReviewDialog(final UserReview review) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_review, null);
        builder.setView(dialogView);

        final TextView vendorNameTextView = dialogView.findViewById(R.id.vendorNameTextView);
        final TextInputEditText reviewContentEditText = dialogView.findViewById(R.id.reviewContentEditText);
        MaterialButton cancelButton = dialogView.findViewById(R.id.cancelButton);
        MaterialButton submitButton = dialogView.findViewById(R.id.submitButton);

        vendorNameTextView.setText("Vendor: " + review.getVendorId());
        reviewContentEditText.setText(review.getComment());

        final androidx.appcompat.app.AlertDialog dialog = builder.create();

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        submitButton.setOnClickListener(v -> {
            String updatedContent = reviewContentEditText.getText().toString();
            updateReview(review, updatedContent);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void updateReview(UserReview review, String updatedContent) {
        progressLoader = new ProgressLoader(this, "Updating Review", "Please Wait");
        progressLoader.startProgressLoader();

        review.setComment(updatedContent);

        String token = JwtTokenUtil.getToken(this);
        Call<ApiRes<UserReview>> call = apiService.updateReview("Bearer " + token, review.getId(), review);
        call.enqueue(new Callback<ApiRes<UserReview>>() {
            @Override
            public void onResponse(Call<ApiRes<UserReview>> call, Response<ApiRes<UserReview>> response) {
                progressLoader.dismissProgressLoader();
                if (response.isSuccessful() && response.body() != null) {
                    ApiRes<UserReview> apiResponse = response.body();
                    if (apiResponse.getStatus()) {
                        Toast.makeText(UserReviewsActivity.this, "Review updated successfully", Toast.LENGTH_SHORT).show();
                        fetchUserReviews(); // Refresh the list
                    } else {
                        String message = apiResponse.getMessage() != null ? apiResponse.getMessage() : "Failed to update review";
                        Toast.makeText(UserReviewsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserReviewsActivity.this, "Error updating review", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiRes<UserReview>> call, Throwable t) {
                progressLoader.dismissProgressLoader();
                Log.e("UserReviewsActivity", "Request Failed", t);
                Toast.makeText(UserReviewsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
