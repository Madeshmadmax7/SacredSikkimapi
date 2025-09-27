// UserService.java
package com.example.sacredsikkimapi.service;

import com.example.sacredsikkimapi.dto.UserDTO;
import com.example.sacredsikkimapi.entity.User;
import com.example.sacredsikkimapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) throw new RuntimeException("Email exists");
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);
        return toDTO(user);
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid email or password");

        return toDTO(user);
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }
}
