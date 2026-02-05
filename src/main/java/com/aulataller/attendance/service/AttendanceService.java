package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.attendance.AttendanceRequest;
import com.aulataller.attendance.dto.attendance.AttendanceResponse;
import com.aulataller.attendance.entity.AccompanimentCourse;
import com.aulataller.attendance.entity.Attendance;
import com.aulataller.attendance.entity.ServiceType;
import com.aulataller.attendance.entity.User;
import com.aulataller.attendance.entity.enums.Role;
import com.aulataller.attendance.exception.attendance.AccompanimentCourseNotFoundException;
import com.aulataller.attendance.exception.attendance.ServiceTypeNotFoundException;
import com.aulataller.attendance.exception.user.InvalidAttendanceDataException;
import com.aulataller.attendance.exception.user.UserNotFoundException;
import com.aulataller.attendance.repository.AccompanimentCourseRepository;
import com.aulataller.attendance.repository.AttendanceRepository;
import com.aulataller.attendance.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final UserService userService;
    private final ServiceTypeRepository serviceTypeRepository;
    private final AccompanimentCourseRepository accompanimentCourseRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceResponse registerAttendance(AttendanceRequest request) {
        User user = userService.findByNationalId(request.getNationalId()).orElseThrow(() ->
                new UserNotFoundException(request.getNationalId()));
        validateRequiredFieldsByRole(user.getRole(), request);

        ServiceType serviceType = serviceTypeRepository.findById(request.getServiceTypeId()).orElseThrow(() ->
                new ServiceTypeNotFoundException(request.getServiceTypeId()));

        AccompanimentCourse accompanimentCourse = null;
        if (request.getAccompanimentCourseId() != null) {
            accompanimentCourse = accompanimentCourseRepository.findById(request.getAccompanimentCourseId()).orElseThrow(() ->
                    new AccompanimentCourseNotFoundException(request.getAccompanimentCourseId()));
        }

        Attendance attendance = Attendance.builder()
                .sessionCode(generateSessionCode())
                .user(user)
                .accompanimentCourse(accompanimentCourse)
                .serviceType(serviceType)
                .estimatedHours(request.getEstimatedHours())
                .authorization(request.getAuthorization())
                .comments(request.getComments())
                .build();
        return AttendanceResponse.from(attendanceRepository.save(attendance));
    }

    private String generateSessionCode() {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();
    }

    public void validateRequiredFieldsByRole(Role role, AttendanceRequest request) {
        switch (role) {
            case PROFESOR, MONITOR, ESTUDIANTE, INVITADO -> validateGeneralFields(request);
            default -> throw new InvalidAttendanceDataException("Rol no soportado");
        }
    }

    public void validateGeneralFields(AttendanceRequest request) {
        if (request.getServiceTypeId() == null) {
            throw new InvalidAttendanceDataException(
                    "El campo tipo de servicio es obligatorios"
            );
        }
    }
}