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

    public static Point2f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.x * width + width / 2.0F, -vertex.y * height + height / 2.0F);
    }
}
