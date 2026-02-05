package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNationalId(String nationalId);

    boolean existsByNationalId(String nationalId);

    boolean existsByEmail(String email);
}