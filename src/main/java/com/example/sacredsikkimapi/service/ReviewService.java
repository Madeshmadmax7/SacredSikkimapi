package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(Long userId, String content);
    List<ReviewDTO> listReviews();
}
