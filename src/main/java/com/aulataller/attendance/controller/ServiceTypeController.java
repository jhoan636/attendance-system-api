package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @GetMapping
    public List<IdNameDto> getServiceTypes() {
        return serviceTypeService.getActiveServiceTypes();
    }
}