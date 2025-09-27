// src/main/java/com/example/sacredsikkimapi/service/EventService.java
package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.EventDTO;
import com.example.sacredsikkimapi.mapper.EventMapper;
import com.example.sacredsikkimapi.entity.Event;
import com.example.sacredsikkimapi.repository.EventRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository repo;

    public EventDTO create(EventDTO dto) {
        Event saved = repo.save(EventMapper.toEntity(dto));
        return EventMapper.toDto(saved);
    }

    public EventDTO update(Long id, EventDTO dto) {
        Event existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setImageUrls(dto.getImageUrls());
        existing.setLocation(dto.getLocation());
        if (dto.getDate() != null) existing.setDate(LocalDate.parse(dto.getDate()));
        Event updated = repo.save(existing);
        return EventMapper.toDto(updated);
    }

    public EventDTO getById(Long id) {
        return repo.findById(id).map(EventMapper::toDto).orElse(null);
    }

    public List<EventDTO> getAll() {
        return repo.findAll().stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    public List<EventDTO> getByDate(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return repo.findByDate(date).stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    public List<EventDTO> getBetween(String from, String to) {
        LocalDate f = LocalDate.parse(from);
        LocalDate t = LocalDate.parse(to);
        return repo.findByDateBetween(f, t).stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
