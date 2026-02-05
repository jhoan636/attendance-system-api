package com.aulataller.attendance.exception.user;

public class InvalidAttendanceDataException extends RuntimeException {
    public InvalidAttendanceDataException(String message) {
        super(message);
    }
}