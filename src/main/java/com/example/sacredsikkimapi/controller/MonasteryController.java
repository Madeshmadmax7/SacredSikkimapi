package com.example.sacredsikkimapi.controller;

import com.example.sacredsikkimapi.dto.MonasteryDTO;
import com.example.sacredsikkimapi.service.MonasteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monasteries")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://monastery360.netlify.app")
public class MonasteryController {

    private final MonasteryService service;

    @GetMapping
    public List<MonasteryDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MonasteryDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public MonasteryDTO create(@RequestBody MonasteryDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public MonasteryDTO update(@PathVariable String id, @RequestBody MonasteryDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}