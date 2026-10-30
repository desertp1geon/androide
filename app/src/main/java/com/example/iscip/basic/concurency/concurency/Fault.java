package com.example.iscip.basic.concurency.concurency;

public class Fault {

    private final String message;
    private final Throwable cause;

    public Fault(String message) {
        this(message, null);
    }

    public Fault(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}