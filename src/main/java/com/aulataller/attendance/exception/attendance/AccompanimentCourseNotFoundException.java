package com.aulataller.attendance.exception.attendance;

public class AccompanimentCourseNotFoundException extends RuntimeException {
    public AccompanimentCourseNotFoundException(Long id) {
        super("No se encontró la asignatura de acompañamiento con ID: : " + id);
    }
}