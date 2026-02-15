package com.aulataller.attendance.dto.attendance;

import com.aulataller.attendance.entity.Attendance;
import com.aulataller.attendance.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class AttendanceResponse {
    private String sessionCode;

    private User user;
    private String serviceType;
    private String accompanimentCourse;
    private BigDecimal estimatedHours;
    private String comments;

    private Instant createdAt;

    public static AttendanceResponse from(Attendance attendance) {
        return AttendanceResponse.builder()
                .sessionCode(attendance.getSessionCode())
                .user(attendance.getUser())
                .serviceType(attendance.getServiceType() != null
                        ? attendance.getServiceType().getName()
                        : null)
                .accompanimentCourse(attendance.getAccompanimentCourse() != null
                        ? attendance.getAccompanimentCourse().getName()
                        : null)
                .estimatedHours(attendance.getEstimatedHours())
                .comments(attendance.getComments())
                .createdAt(attendance.getCreatedAt())
                .build();
    }
}