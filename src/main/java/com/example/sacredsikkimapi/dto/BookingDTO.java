package com.example.sacredsikkimapi.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private String monasteryId;
    private String name;
    private String email;
    private String date;
    private int persons;

    private Long userId;
    private String userName;
    private String monasteryTitle;
    private String monasteryLocation;
}
