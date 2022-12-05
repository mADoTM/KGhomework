package com.churkovainteam.kghomework.math;

public final class Vector2f {
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.x, x) == 0 && Float.compare(vector2f.y, y) == 0;
    }
}
