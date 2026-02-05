package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.service.AccompanimentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accompaniment-courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccompanimentCourseController {
    private final AccompanimentCourseService accompanimentCourseService;

    @GetMapping
    public List<IdNameDto> getAccompanimentCourses() {
        return accompanimentCourseService.getActiveCourses();
    }
}