package com.example.sacredsikkimapi.mapper;


import com.example.sacredsikkimapi.dto.EventDTO;
import com.example.sacredsikkimapi.entity.Event;

import java.time.LocalDate;

public class EventMapper {
    public static EventDTO toDto(Event e) {
        if (e == null) return null;
        EventDTO dto = new EventDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setImageUrls(e.getImageUrls());
        dto.setDate(e.getDate() != null ? e.getDate().toString() : null);
        dto.setLocation(e.getLocation());
        return dto;
    }

    public static Event toEntity(EventDTO dto) {
        if (dto == null) return null;
        Event e = new Event();
        e.setId(dto.getId());
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setImageUrls(dto.getImageUrls());
        e.setLocation(dto.getLocation());
        if (dto.getDate() != null && !dto.getDate().isEmpty()) {
            e.setDate(LocalDate.parse(dto.getDate()));
        }
        return e;
    }
}
