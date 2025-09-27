package com.example.sacredsikkimapi.security;

import com.example.sacredsikkimapi.entity.User;
import com.example.sacredsikkimapi.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;

    public AuthenticatedUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get email of logged-in user
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername(); // email
        }

        return null;
    }

    // Get full User entity
    public User getUser() {
        String email = getUsername();
        if (email == null) return null;
        return userRepository.findByEmail(email).orElse(null);
    }
}
