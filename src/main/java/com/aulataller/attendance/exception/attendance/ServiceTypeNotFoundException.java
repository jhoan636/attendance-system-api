package com.aulataller.attendance.exception.attendance;

public class ServiceTypeNotFoundException extends RuntimeException {
    public ServiceTypeNotFoundException(Long id) {
        super("No se encontró el tipo de servicio con ID: " + id);
    }
}