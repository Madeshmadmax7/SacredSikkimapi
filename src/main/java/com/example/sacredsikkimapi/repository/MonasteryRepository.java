package com.example.sacredsikkimapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sacredsikkimapi.entity.Monastery;

public interface MonasteryRepository extends JpaRepository<Monastery, String> {
    
}
