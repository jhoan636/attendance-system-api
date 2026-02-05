package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.RoleAccessCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface RoleAccessCodeRepository extends JpaRepository<RoleAccessCode, Long> {
    @Query("""
                SELECT r FROM RoleAccessCode r
                WHERE r.code = :code
                  AND r.active = true
                  AND :today BETWEEN r.validFrom AND r.validUntil
            """)
    Optional<RoleAccessCode> findValidCode(
            @Param("code") String code,
            @Param("today") LocalDate today
    );
}