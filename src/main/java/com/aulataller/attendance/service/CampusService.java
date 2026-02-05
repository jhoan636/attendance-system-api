package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.user.IdNameDto;
import com.aulataller.attendance.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {
    private final CampusRepository campusRepository;

    public List<IdNameDto> getActiveCampuses() {
        return campusRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(campus -> new IdNameDto(campus.getId(), campus.getName()))
                .toList();
    }
}