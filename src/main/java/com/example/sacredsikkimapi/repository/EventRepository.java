package com.example.sacredsikkimapi.repository;

import com.example.sacredsikkimapi.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDate(LocalDate date);
    List<Event> findByDateBetween(LocalDate from, LocalDate to);
}
