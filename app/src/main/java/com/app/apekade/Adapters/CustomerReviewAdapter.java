package com.app.apekade.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Model.Rating;
import com.app.apekade.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CustomerReviewAdapter extends RecyclerView.Adapter<CustomerReviewAdapter.ViewHolder> {

    private List<Rating> ratings;
    private SimpleDateFormat dateFormat;

    public CustomerReviewAdapter(List<Rating> ratings) {
        this.ratings = ratings;
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rating rating = ratings.get(position);
        holder.ratingBar.setRating((rating.getItemQualityRating() + rating.getCommunicationRating() + rating.getShippingSpeedRating()) / 3);
        holder.tvCustomerName.setText(rating.getCustomerId()); // You might want to fetch the actual customer name
        holder.tvReviewDate.setText(dateFormat.format(rating.getAdded()));
        holder.tvReviewContent.setText(rating.getComment());
    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }

    public void updateReviews(List<Rating> newRatings) {
        this.ratings = newRatings;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView tvCustomerName;
        TextView tvReviewDate;
        TextView tvReviewContent;

        ViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvReviewDate = itemView.findViewById(R.id.tvReviewDate);
            tvReviewContent = itemView.findViewById(R.id.tvReviewContent);
        }
    }
}