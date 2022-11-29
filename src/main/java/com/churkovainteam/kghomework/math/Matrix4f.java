package com.churkovainteam.kghomework.math;

public class Matrix4f {
    public float m00;
    public float m01;
    public float m02;
    public float m03;

    public float m10;
    public float m11;
    public float m12;
    public float m13;

    public float m20;
    public float m21;
    public float m22;
    public float m23;

    public float m30;
    public float m31;
    public float m32;
    public float m33;

    public Matrix4f() {
    }

    public Matrix4f(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;

        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public Matrix4f(Matrix4f matrix4f) {
        this(matrix4f.m00, matrix4f.m01, matrix4f.m02, matrix4f.m03,
                matrix4f.m10, matrix4f.m11, matrix4f.m12, matrix4f.m13,
                matrix4f.m20, matrix4f.m21, matrix4f.m22, matrix4f.m23,
                matrix4f.m30, matrix4f.m31, matrix4f.m32, matrix4f.m33);
    }

    public Matrix4f(float[] vertices) {
        if(vertices == null) {
            throw new IllegalStateException("v is null");
        }

        if(vertices.length < 16) {
            throw new IllegalArgumentException("Can not create a Matrix 4x4 with that length = " + vertices.length);
        }

        this.m00 = vertices[0];
        this.m01 = vertices[1];
        this.m02 = vertices[2];
        this.m03 = vertices[3];

        this.m10 = vertices[4];
        this.m11 = vertices[5];
        this.m12 = vertices[6];
        this.m13 = vertices[7];

        this.m20 = vertices[8];
        this.m21 = vertices[9];
        this.m22 = vertices[10];
        this.m23 = vertices[11];

        this.m30 = vertices[12];
        this.m31 = vertices[13];
        this.m32 = vertices[14];
        this.m33 = vertices[15];
    }

    public final void mul(float scalar)
    {
        m00 *= scalar;
        m01 *= scalar;
        m02 *= scalar;
        m03 *= scalar;
        m10 *= scalar;
        m11 *= scalar;
        m12 *= scalar;
        m13 *= scalar;
        m20 *= scalar;
        m21 *= scalar;
        m22 *= scalar;
        m23 *= scalar;
        m30 *= scalar;
        m31 *= scalar;
        m32 *= scalar;
        m33 *= scalar;
    }

    public final void mul(Matrix4f m1)
    {

        final var m00 = this.m00*m1.m00 + this.m01*m1.m10 +
                this.m02*m1.m20 + this.m03*m1.m30;
        final var m01 = this.m00*m1.m01 + this.m01*m1.m11 +
                this.m02*m1.m21 + this.m03*m1.m31;
        final var m02 = this.m00*m1.m02 + this.m01*m1.m12 +
                this.m02*m1.m22 + this.m03*m1.m32;
        final var m03 = this.m00*m1.m03 + this.m01*m1.m13 +
                this.m02*m1.m23 + this.m03*m1.m33;

        final var m10 = this.m10*m1.m00 + this.m11*m1.m10 +
                this.m12*m1.m20 + this.m13*m1.m30;
        final var m11 = this.m10*m1.m01 + this.m11*m1.m11 +
                this.m12*m1.m21 + this.m13*m1.m31;
        final var m12 = this.m10*m1.m02 + this.m11*m1.m12 +
                this.m12*m1.m22 + this.m13*m1.m32;
        final var m13 = this.m10*m1.m03 + this.m11*m1.m13 +
                this.m12*m1.m23 + this.m13*m1.m33;

        final var m20 = this.m20*m1.m00 + this.m21*m1.m10 +
                this.m22*m1.m20 + this.m23*m1.m30;
        final var m21 = this.m20*m1.m01 + this.m21*m1.m11 +
                this.m22*m1.m21 + this.m23*m1.m31;
        final var m22 = this.m20*m1.m02 + this.m21*m1.m12 +
                this.m22*m1.m22 + this.m23*m1.m32;
        final var m23 = this.m20*m1.m03 + this.m21*m1.m13 +
                this.m22*m1.m23 + this.m23*m1.m33;

        final var m30 = this.m30*m1.m00 + this.m31*m1.m10 +
                this.m32*m1.m20 + this.m33*m1.m30;
        final var m31 = this.m30*m1.m01 + this.m31*m1.m11 +
                this.m32*m1.m21 + this.m33*m1.m31;
        final var m32 = this.m30*m1.m02 + this.m31*m1.m12 +
                this.m32*m1.m22 + this.m33*m1.m32;
        final var m33 = this.m30*m1.m03 + this.m31*m1.m13 +
                this.m32*m1.m23 + this.m33*m1.m33;

        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        this.m30 = m30; this.m31 = m31; this.m32 = m32; this.m33 = m33;
    }
}