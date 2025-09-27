package com.example.sacredsikkimapi.mapper;

import com.example.sacredsikkimapi.dto.BookingDTO;
import com.example.sacredsikkimapi.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .name(booking.getName())
                .email(booking.getEmail())
                .date(booking.getDate())
                .persons(booking.getPersons())
                .userId(booking.getUser() != null ? booking.getUser().getId() : null)
                .userName(booking.getUser() != null ? booking.getUser().getName() : null)
                .monasteryId(booking.getMonastery() != null ? booking.getMonastery().getId() : null)
                .monasteryTitle(booking.getMonastery() != null ? booking.getMonastery().getTitle() : "Unknown")
                .monasteryLocation(booking.getMonastery() != null ? booking.getMonastery().getLocation() : "Unknown")
                .build();
    }

    public static Booking toEntity(BookingDTO dto) {
        return Booking.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .date(dto.getDate())
                .persons(dto.getPersons())
                .build();
    }
}
