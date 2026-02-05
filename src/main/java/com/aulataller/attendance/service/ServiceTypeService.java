package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    public List<IdNameDto> getActiveServiceTypes() {
        return serviceTypeRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(type -> new IdNameDto(type.getId(), type.getName()))
                .toList();
    }
}