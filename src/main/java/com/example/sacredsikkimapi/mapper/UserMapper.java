package com.example.sacredsikkimapi.mapper;

import com.example.sacredsikkimapi.dto.UserDTO;
import com.example.sacredsikkimapi.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRoles().isEmpty() ? null : user.getRoles().iterator().next().name())
                .bookings(user.getBookings() != null
                        ? user.getBookings().stream().map(BookingMapper::toDTO).collect(Collectors.toList())
                        : null)
                .build();
    }
}
