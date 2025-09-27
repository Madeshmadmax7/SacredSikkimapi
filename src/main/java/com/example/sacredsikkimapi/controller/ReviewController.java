package com.example.sacredsikkimapi.controller;

import com.example.sacredsikkimapi.dto.ReviewDTO;
import com.example.sacredsikkimapi.entity.User;
import com.example.sacredsikkimapi.repository.UserRepository;
import com.example.sacredsikkimapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://monastery360.netlify.app")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO dto, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Please login to submit a review.");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReviewDTO created = reviewService.createReview(user.getId(), dto.getContent());
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> listReviewsForUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(
                reviewService.listReviews().stream()
                        .filter(r -> r.getUserId().equals(user.getId()))
                        .toList()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> listAllReviews() {
        return ResponseEntity.ok(reviewService.listReviews());
    }
}
