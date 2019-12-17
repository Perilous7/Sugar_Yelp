package com.sugar.ascending.repository;

import com.sugar.ascending.model.Review;

import java.util.List;

public interface ReviewDao {
    boolean save(Review review);
    boolean update(Review review);
    boolean delete(String reviewName);
    List<Review> getReviews();
    List<Review> getReviewById(int id1,int id2);
    List<Review> getReviewByBusinessId(int bid);
    List<Review> getReviewByCustomerId(int cid);
}
