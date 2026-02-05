package com.aulataller.attendance.service;

import com.aulataller.attendance.dto.user.CreateUserRequest;
import com.aulataller.attendance.dto.user.UserResponse;
import com.aulataller.attendance.entity.AcademicProgram;
import com.aulataller.attendance.entity.Campus;
import com.aulataller.attendance.entity.User;
import com.aulataller.attendance.entity.enums.Role;
import com.aulataller.attendance.exception.user.InvalidUserDataException;
import com.aulataller.attendance.exception.user.UserAlreadyExistsException;
import com.aulataller.attendance.repository.AcademicProgramRepository;
import com.aulataller.attendance.repository.CampusRepository;
import com.aulataller.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CampusRepository campusRepository;
    private final AcademicProgramRepository academicProgramRepository;
    private final RoleResolutionService roleResolutionService;

    public Optional<User> findByNationalId(String nationalId) {
        return userRepository.findByNationalId(nationalId);
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByNationalId(request.getNationalId())) {
            throw new UserAlreadyExistsException(
                    request.getNationalId()
            );
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidUserDataException("El correo ya se encuentra registrado: " +
                    request.getEmail()
            );
        }

        Role resolvedRole =
                roleResolutionService.resolveRole(request.getRoleAccessCode());
        validateRequiredFieldsByRole(resolvedRole, request);

        Campus campus = null;
        if (request.getCampusId() != null) {
            campus = campusRepository.findById(request.getCampusId())
                    .orElseThrow(() -> new InvalidUserDataException("No se encontró el campus con ID: " + request.getCampusId()));
        }

        AcademicProgram academicProgram = null;
        if (request.getAcademicProgramId() != null) {
            academicProgram = academicProgramRepository.findById(request.getAcademicProgramId())
                    .orElseThrow(() -> new InvalidUserDataException("No se encontró el programa académico con ID: " + request.getAcademicProgramId()));
        }

        User user = User.builder()
                .nationalId(request.getNationalId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .campus(campus)
                .academicProgram(academicProgram)
                .semester(request.getSemester())
                .role(resolvedRole)
                .build();
        return UserResponse.from(userRepository.save(user));
    }

    public void validateRequiredFieldsByRole(Role role, CreateUserRequest request) {
        switch (role) {
            case PROFESOR -> validateTeacherFields(request);
            case MONITOR, ESTUDIANTE -> validateStudentAndMonitorFields(request);
            case INVITADO -> validateInvitedFields(request);
            default -> throw new InvalidUserDataException("Rol no soportado");
        }
    }

    public void validateStudentAndMonitorFields(CreateUserRequest request) {
        if (request.getCampusId() == null || request.getAcademicProgramId() == null ||
                request.getSemester() == null) {
            throw new InvalidUserDataException(
                    "El Estudiante o Monitor requiere: la sede, programa academico y semestre"
            );
        }
    }

    public void validateTeacherFields(CreateUserRequest request) {
        if (request.getCampusId() == null || request.getAcademicProgramId() == null) {
            throw new InvalidUserDataException(
                    "El Profesor requiere: la sede o el programa academico"
            );
        }
    }

    public void validateInvitedFields(CreateUserRequest request) {
        if (request.getCampusId() == null) {
            throw new InvalidUserDataException(
                    "El Invitado requiere: la sede"
            );
        }
    }
}