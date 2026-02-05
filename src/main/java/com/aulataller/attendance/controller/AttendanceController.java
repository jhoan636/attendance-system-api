package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.attendance.AttendanceRequest;
import com.aulataller.attendance.dto.attendance.AttendanceResponse;
import com.aulataller.attendance.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> register(@Valid @RequestBody AttendanceRequest dto) {
        log.info("Registro de asistencia iniciado: {}", dto.getNationalId());
        AttendanceResponse response =
                attendanceService.registerAttendance(dto);
        log.info("Asistencia registranda para el usuario con código: {}", dto.getNationalId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}