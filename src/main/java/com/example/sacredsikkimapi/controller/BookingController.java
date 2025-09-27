package com.example.sacredsikkimapi.controller;

import com.example.sacredsikkimapi.dto.BookingDTO;
import com.example.sacredsikkimapi.security.AuthenticatedUser;
import com.example.sacredsikkimapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://monastery360.netlify.app")
public class BookingController {

    private final BookingService service;
    private final AuthenticatedUser authenticatedUser;

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO dto) {
        var user = authenticatedUser.getUser();
        if (user == null)
            return ResponseEntity.status(401).build();

        dto.setUserId(user.getId());
        dto.setUserName(user.getName());

        BookingDTO saved = service.create(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookingDTO>> getUserBookings() {
        var user = authenticatedUser.getUser();
        if (user == null)
            return ResponseEntity.status(401).build();

        List<BookingDTO> bookings = service.getUserBookings(user.getId());
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @RequestBody BookingDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
