package com.nick.pm.entity;


public enum Status {
    WAITING(1), IMPLEMENTATION(2), VERYFYING(3), RELEASING(4);

    int status;
    Status(int i) {
        this.status = i;
    }

    public int getStatus() {
        return status;
    }
}
