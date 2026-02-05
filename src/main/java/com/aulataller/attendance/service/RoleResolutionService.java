package com.aulataller.attendance.service;

import com.aulataller.attendance.entity.RoleAccessCode;
import com.aulataller.attendance.entity.enums.Role;
import com.aulataller.attendance.exception.user.InvalidUserDataException;
import com.aulataller.attendance.repository.RoleAccessCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RoleResolutionService {
    private final RoleAccessCodeRepository repository;

    public Role resolveRole(String roleAccessCode) {

        if (roleAccessCode == null || roleAccessCode.isBlank()) {
            return Role.ESTUDIANTE;
        }

        RoleAccessCode accessCode = repository
                .findValidCode(roleAccessCode, LocalDate.now())
                .orElseThrow(() ->
                        new InvalidUserDataException("Código de acceso inválido o vencido")
                );
        return accessCode.getRole();
    }
}