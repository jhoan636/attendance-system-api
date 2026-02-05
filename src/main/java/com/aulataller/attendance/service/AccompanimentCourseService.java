package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.repository.AccompanimentCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccompanimentCourseService {
    private final AccompanimentCourseRepository accompanimentCourseRepository;

    public List<IdNameDto> getActiveCourses() {
        return accompanimentCourseRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(course -> new IdNameDto(course.getId(), course.getName()))
                .toList();
    }
}