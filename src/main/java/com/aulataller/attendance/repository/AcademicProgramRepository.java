package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.AcademicProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Long> {
    List<AcademicProgram> findByActiveTrueOrderByNameAsc();
}