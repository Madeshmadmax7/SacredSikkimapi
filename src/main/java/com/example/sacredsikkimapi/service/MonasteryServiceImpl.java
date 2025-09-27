package com.example.sacredsikkimapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sacredsikkimapi.dto.MonasteryDTO;
import com.example.sacredsikkimapi.entity.Monastery;
import com.example.sacredsikkimapi.mapper.MonasteryMapper;
import com.example.sacredsikkimapi.repository.MonasteryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonasteryServiceImpl implements MonasteryService {
    private final MonasteryRepository repo;
    private final MonasteryMapper mapper;

    @Override
    public List<MonasteryDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public MonasteryDTO getById(String id) {
        return repo.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public MonasteryDTO create(MonasteryDTO dto) {
        Monastery entity = mapper.toEntity(dto);
        Monastery saved = repo.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public MonasteryDTO update(String id, MonasteryDTO dto) {
        Monastery entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        // Use mapper to update entity from DTO
        mapper.updateEntityFromDTO(dto, entity);

        Monastery saved = repo.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}