package com.churkovainteam.kghomework.math;

public final class Point2f {
    public float x;
    public float y;

    public Point2f() {
    }

    public Point2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2f(Point2f point) {
        this.x = point.x;
        this.y = point.y;
    }
}
