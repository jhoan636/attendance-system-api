package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.repository.AcademicProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicProgramService {
    private final AcademicProgramRepository academicProgramRepository;

    public List<IdNameDto> getActivePrograms() {
        return academicProgramRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(program -> new IdNameDto(program.getId(), program.getName()))
                .toList();
    }
}