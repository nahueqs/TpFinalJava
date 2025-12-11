package com.crudJava.demo.enums;

public enum CartStatus {

    PENDING,
    FAILED,
    COMPLETED;


    public static CartStatus fromString(String status) {
        if (status == null || status.trim().isEmpty()) {
            return null;
        }
        return CartStatus.valueOf(status.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return this.name();
    }
}