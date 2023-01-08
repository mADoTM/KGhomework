package com.churkovainteam.kghomework.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix4fTest {
    @Test
    public void mulTest() {
        var testMatrix = new Matrix4f();
        testMatrix.mul(new Matrix4f());
        assertEquals(new Matrix4f().toString(), testMatrix.toString());

        testMatrix = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 1, 2, 3,
                4, 5, 6, 7
        });

        var sourceMatrix = new Matrix4f(new float[] {
                4, 2, 2, 1,
                4, 2, 5, 8,
                4, 5, 6, 9,
                3, 6, 2, 0
        });

        testMatrix.mul(sourceMatrix);
        assertEquals(new Matrix4f(new float[] {
                36, 45, 38, 44,
                96, 105, 98, 116,
                57, 48, 41, 35,
                81, 90, 83, 98
        }).toString(), testMatrix.toString());
    }
}