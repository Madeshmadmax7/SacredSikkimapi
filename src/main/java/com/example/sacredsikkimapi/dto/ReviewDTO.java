package com.example.sacredsikkimapi.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String content;
}
