package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO create(BookingDTO dto);
    List<BookingDTO> getAll();
    BookingDTO getById(Long id);
    BookingDTO update(Long id, BookingDTO dto);
    void delete(Long id);
    List<BookingDTO> getUserBookings(Long userId);
}
