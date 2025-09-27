package com.example.sacredsikkimapi.service;

import java.util.List;

import com.example.sacredsikkimapi.dto.MonasteryDTO;

public interface MonasteryService {
    List<MonasteryDTO> getAll();
    MonasteryDTO getById(String id);
    MonasteryDTO create(MonasteryDTO dto);
    MonasteryDTO update(String id, MonasteryDTO dto);
    void delete(String id);
}