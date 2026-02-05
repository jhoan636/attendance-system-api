package com.aulataller.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "academic_programs")
@Getter
public class AcademicProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private boolean active;
}