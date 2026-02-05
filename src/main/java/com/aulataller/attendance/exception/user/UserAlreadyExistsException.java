package com.aulataller.attendance.exception.user;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String nationalId) {
        super("No fue posible crear el usuario porque ya existe uno con el documento: " + nationalId);
    }
}