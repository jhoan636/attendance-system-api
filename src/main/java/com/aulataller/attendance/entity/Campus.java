package com.aulataller.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "campuses")
@Getter
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private boolean active;
}