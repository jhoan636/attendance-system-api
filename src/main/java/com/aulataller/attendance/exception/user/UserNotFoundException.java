package com.aulataller.attendance.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String nationalId) {
        super("No se encontró el usuario con el documento: " + nationalId);
    }
}