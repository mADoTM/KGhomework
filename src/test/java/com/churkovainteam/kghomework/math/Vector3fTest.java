package com.churkovainteam.kghomework.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3fTest {
    @Test
    public void subMethod() {
        Vector3f testVector = new Vector3f();
        Vector3f source1 = new Vector3f();
        Vector3f source2 = new Vector3f();

        assertThrows(IllegalArgumentException.class, () -> testVector.sub(null, null));
        testVector.sub(source1, source2);
        assertEquals(testVector, source1);
        assertEquals(testVector, source2);

        source1 = new Vector3f(1, 1, 1);
        source2 = new Vector3f(1, 4, 6);
        testVector.sub(source1, source2);
        assertEquals(testVector, new Vector3f(0, -3, -5));
    }


    @Test
    public void crossTest() {
        Vector3f testVector = new Vector3f();
        Vector3f source1 = new Vector3f();
        Vector3f source2 = new Vector3f();

        assertThrows(IllegalArgumentException.class, () -> testVector.cross(null, null));

        testVector.cross(source1, source2);
        assertEquals(testVector, source1);
        assertEquals(testVector, source2);

        source1 = new Vector3f(1, 2, 3);
        source2 = new Vector3f(4, 5, 6);
        testVector.cross(source1, source2);
        assertEquals(testVector, new Vector3f(-3, 6, -3));
    }

    @Test
    public void normalizeTest() {
        Vector3f testVector = new Vector3f();
        testVector.normalize();
        assertEquals(new Vector3f(), testVector);

        testVector = new Vector3f(0, 3, 4);
        testVector.normalize();
        assertEquals(new Vector3f(0, 0.6f, 0.8f), testVector);
    }

    @Test
    public void dotTest() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3f().dot(null));

        Vector3f testVector = new Vector3f();
        Vector3f source1 = new Vector3f();
        assertEquals(0, testVector.dot(source1));

        source1 = new Vector3f(1, 2, 3);
        assertEquals(0, testVector.dot(source1));

        testVector = new Vector3f(3, 4, 5);
        assertEquals(26, testVector.dot(source1));
    }

    @Test
    public void addTest() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3f().add(null, null));
        assertThrows(IllegalArgumentException.class, () -> new Vector3f().add(null));

        var testVector = new Vector3f();
        testVector.add(new Vector3f(1,2,3));
        assertEquals(new Vector3f(1,2,3), testVector);

        testVector.add(new Vector3f(1,2,3), new Vector3f(-1,-2,-3));
        assertEquals(new Vector3f(), testVector);
    }

    @Test
    public void testLength() {
        var testVector = new Vector3f(0,0,0);
        assertEquals(0, testVector.length());
        testVector = new Vector3f(0,3,4);
        assertEquals(5, testVector.length());
    }
}