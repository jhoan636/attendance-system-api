package com.aulataller.attendance.exception.user;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String nationalId) {
        super("Se encontró el usuario con el documento: " + nationalId);
    }
}