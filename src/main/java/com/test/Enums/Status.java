package com.test.Enums;

public enum Status {
    VERIFIED(0),
    UNVERIFIED(1);

   int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
