package com.churkovainteam.kghomework.math;


public final class Vector3f {
    public float x;
    public float y;
    public float z;

    public Vector3f() {
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

        float x = v1.y * v2.z - v1.z * v2.y;
        float y = v2.x * v1.z - v2.z * v1.x;

        this.z = v1.x * v2.y - v1.y * v2.x;
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        float norm = (float)
                (1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));

        this.x *= norm;
        this.y *= norm;
        this.z *= norm;
    }

    public float dot(Vector3f v1) {
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

    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < MathSettings.EPS
                && Math.abs(y - other.y) < MathSettings.EPS
                && Math.abs(z - other.z) < MathSettings.EPS;
    }
}
