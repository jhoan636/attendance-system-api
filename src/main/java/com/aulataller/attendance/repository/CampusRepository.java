package com.aulataller.attendance.repository;

import com.aulataller.attendance.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    List<Campus> findByActiveTrueOrderByNameAsc();
}