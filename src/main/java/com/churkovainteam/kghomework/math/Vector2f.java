package com.churkovainteam.kghomework.math;

public final class Vector2f {
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x, y;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Vector2f.class) {
            return ((Vector2f) obj).x == x && ((Vector2f) obj).y == y;
        }

        return false;
    }
}
