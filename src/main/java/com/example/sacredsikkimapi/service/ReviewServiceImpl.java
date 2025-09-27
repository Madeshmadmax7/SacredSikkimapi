package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.ReviewDTO;
import com.example.sacredsikkimapi.mapper.ReviewMapper;
import com.example.sacredsikkimapi.entity.Review;
import com.example.sacredsikkimapi.entity.User;
import com.example.sacredsikkimapi.repository.ReviewRepository;
import com.example.sacredsikkimapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewDTO createReview(Long userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Review review = Review.builder()
                .user(user)
                .content(content)
                .build();
        return ReviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDTO> listReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }
}
