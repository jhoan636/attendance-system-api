package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.service.AcademicProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/academic-programs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AcademicProgramController {
    private final AcademicProgramService academicProgramService;

    @GetMapping
    public List<IdNameDto> getAcademicPrograms() {
        return academicProgramService.getActivePrograms();
    }
}