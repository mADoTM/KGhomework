package com.churkovainteam.kghomework.math;

public final class Point2f {
    public double x;
    public double y;

    public Point2f() {
    }

    public Point2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2f(Point2f point) {
        this.x = point.x;
        this.y = point.y;
    }
}
