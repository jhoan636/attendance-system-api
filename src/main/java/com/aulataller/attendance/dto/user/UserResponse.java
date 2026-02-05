package com.aulataller.attendance.dto.user;

import com.aulataller.attendance.entity.User;
import com.aulataller.attendance.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    // Identidad
    private String nationalId;
    private String fullName;
    private String email;
    private String phone;

    // Académico
    private String campus;
    private String academicProgram;
    private Integer semester;

    // Rol
    private Role role;

    // Estado
    private boolean active;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .nationalId(user.getNationalId())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .campus(user.getCampus().getName())
                .academicProgram(user.getAcademicProgram().getName())
                .semester(user.getSemester())
                .role(user.getRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }
}