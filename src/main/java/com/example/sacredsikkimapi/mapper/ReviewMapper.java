package com.example.sacredsikkimapi.mapper;

import com.example.sacredsikkimapi.dto.ReviewDTO;
import com.example.sacredsikkimapi.entity.Review;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setContent(review.getContent());
        dto.setUserId(review.getUser().getId());
        dto.setUserName(review.getUser().getName());
        return dto;
    }
}
