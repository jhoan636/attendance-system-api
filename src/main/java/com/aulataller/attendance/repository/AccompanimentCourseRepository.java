package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.AccompanimentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccompanimentCourseRepository extends JpaRepository<AccompanimentCourse, Long> {
    List<AccompanimentCourse> findByActiveTrueOrderByNameAsc();

}