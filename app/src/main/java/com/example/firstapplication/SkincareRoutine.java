package com.example.firstapplication;

public class SkincareRoutine {
    private int time;
    private SkincareProduct product;

    public SkincareRoutine(int time, SkincareProduct product) {
        this.time = time;
        this.product = product;
    }

    public int getTime() {
        return time;
    }

    public SkincareProduct getProduct() {
        return product;
    }
}
