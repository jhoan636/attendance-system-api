package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/campuses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CampusController {

    private final CampusService campusService;

    @GetMapping
    public List<IdNameDto> getCampuses() {
        return campusService.getActiveCampuses();
    }
}