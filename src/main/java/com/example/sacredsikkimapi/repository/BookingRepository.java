package com.example.sacredsikkimapi.repository;

import com.example.sacredsikkimapi.entity.Booking;
import com.example.sacredsikkimapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
