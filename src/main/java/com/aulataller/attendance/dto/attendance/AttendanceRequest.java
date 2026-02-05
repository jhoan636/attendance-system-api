package com.aulataller.attendance.dto.attendance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AttendanceRequest {

    @NotBlank
    private String nationalId;

    @NotNull
    private Long serviceTypeId;
    
    private Long accompanimentCourseId;

    @NotNull
    private BigDecimal estimatedHours;

    @NotNull
    private Boolean authorization;

    private String comments;
}