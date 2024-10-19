package com.app.apekade.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apekade.Model.UserReview;
import com.app.apekade.R;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class UserReviewAdapter extends RecyclerView.Adapter<UserReviewAdapter.ReviewViewHolder> {

    private List<UserReview> reviewList;
    private OnReviewClickListener listener;
    private SimpleDateFormat dateFormat;

    public UserReviewAdapter(List<UserReview> reviewList, OnReviewClickListener listener) {
        this.reviewList = reviewList;
        this.listener = listener;
        this.dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        UserReview review = reviewList.get(position);
        holder.vendorNameTextView.setText("Vendor: " + review.getVendorId());
        holder.commentTextView.setText(review.getComment());
        holder.dateAddedTextView.setText("Added: " + dateFormat.format(review.getAdded()));

        holder.itemView.setOnClickListener(v -> listener.onReviewClick(review));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView vendorNameTextView;
        TextView commentTextView;
        TextView dateAddedTextView;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            vendorNameTextView = itemView.findViewById(R.id.vendorNameTextView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            dateAddedTextView = itemView.findViewById(R.id.dateTextView);
        }
    }

    public interface OnReviewClickListener {
        void onReviewClick(UserReview review);
    }
}
