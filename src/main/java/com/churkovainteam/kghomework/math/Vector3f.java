package com.churkovainteam.kghomework.math;


import java.util.Objects;

public final class Vector3f {
    public double x;
    public double y;
    public double z;

    public Vector3f() {
    }

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f vector3f) {
        this(vector3f.x, vector3f.y, vector3f.z);
    }

    public void sub(Vector3f to, Vector3f from) {
        if (to == null || from == null) {
            throw new IllegalArgumentException("Vector3f can not be null");
        }
        this.x = to.x - from.x;
        this.y = to.y - from.y;
        this.z = to.z - from.z;
    }

    public void cross(Vector3f v1, Vector3f v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vector3f can not be null");
        }

        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v2.x * v1.z - v2.z * v1.x;

        this.z = v1.x * v2.y - v1.y * v2.x;
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        double norm =
                (1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));

        this.x *= norm;
        this.y *= norm;
        this.z *= norm;
    }

    public double dot(Vector3f v1) {
        if (v1 == null) {
            throw new IllegalArgumentException("Vector3f can not be null");
        }
        return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }

    public void add(Vector3f t1, Vector3f t2) {
        this.x = t1.x + t2.x;
        this.y = t1.y + t2.y;
        this.z = t1.z + t2.z;
    }

    public void add(Vector3f t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
    }

    public void rotateAroundX(float angle) {
        double y = (this.y * Math.cos(angle) + this.z * Math.sin(angle));
        double z = (-this.y * Math.sin(angle) + this.z * Math.cos(angle));

        this.y = y;
        this.z = z;
    }

    public void rotateAroundY(double angle) {
        double x = (this.x * Math.cos(angle) + this.z * Math.sin(angle));
        double z = (-this.x * Math.sin(angle) + this.z * Math.cos(angle));

        this.x = x;
        this.z = z;
    }

    public void rotateAroundZ(float angle) {
        double x = (this.x * Math.cos(angle) + this.y * Math.sin(angle));
        double y = (-this.x * Math.sin(angle) + this.y * Math.cos(angle));

        this.x = x;
        this.y = y;
    }

    public void scaleX(double scale) {
        this.x *= scale;
    }

    public void scaleY(double scale) {
        this.y *= scale;
    }

    public void scaleZ(double scale) {
        this.z *= scale;
    }

    public static double angleBetweenTwoVectors(Vector3f vec1, Vector3f vec2) {
        if (vec1 == null || vec2 == null) {
            throw new IllegalArgumentException("Vector can't be null");
        }
        return (vec1.x * vec2.x + vec1.y * vec1.y + vec1.z * vec1.z)
                /
                (vec1.length() * vec2.length());
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Double.compare(vector3f.x, x) == 0 && Double.compare(vector3f.y, y) == 0 && Double.compare(vector3f.z, z) == 0;
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
