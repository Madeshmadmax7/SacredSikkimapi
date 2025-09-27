package com.example.sacredsikkimapi.dto;

import lombok.Data;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrls;
    private String date;
    private String location;
}
