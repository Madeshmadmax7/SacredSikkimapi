package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.BookingDTO;
import com.example.sacredsikkimapi.entity.Booking;
import com.example.sacredsikkimapi.entity.Monastery;
import com.example.sacredsikkimapi.entity.User;
import com.example.sacredsikkimapi.mapper.BookingMapper;
import com.example.sacredsikkimapi.repository.BookingRepository;
import com.example.sacredsikkimapi.repository.MonasteryRepository;
import com.example.sacredsikkimapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final UserRepository userRepo;
    private final MonasteryRepository monasteryRepo;
    private final BookingMapper mapper;

    @Override
    public BookingDTO create(BookingDTO dto) {
        Booking booking = mapper.toEntity(dto);

        // Map user
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        booking.setUser(user);

        // Map monastery
        Monastery monastery = monasteryRepo.findById(dto.getMonasteryId())
                .orElseThrow(() -> new RuntimeException("Monastery not found"));
        booking.setMonastery(monastery);

        Booking saved = repo.save(booking);
        return mapper.toDTO(saved);
    }

    @Override
    public List<BookingDTO> getAll() {
        return repo.findAll().stream()
                .map(BookingMapper::toDTO)
                .toList();
    }

    @Override
    public BookingDTO getById(Long id) {
        return repo.findById(id)
                .map(BookingMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public BookingDTO update(Long id, BookingDTO dto) {
        Booking b = repo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        b.setName(dto.getName());
        b.setEmail(dto.getEmail());
        b.setDate(dto.getDate());
        b.setPersons(dto.getPersons());

        Booking saved = repo.save(b);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<BookingDTO> getUserBookings(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repo.findByUser(user).stream()
                .map(BookingMapper::toDTO)
                .toList();
    }
}
