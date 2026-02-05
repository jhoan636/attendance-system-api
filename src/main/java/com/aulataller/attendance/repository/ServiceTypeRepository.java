package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    List<ServiceType> findByActiveTrueOrderByNameAsc();
}