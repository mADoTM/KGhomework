package com.churkovainteam.kghomework.math;

public final class Matrix4f {

    private double[][] matrix;

    public Matrix4f() {
        this.matrix = new double[4][4];
    }

    public Matrix4f(double m00, double m01, double m02, double m03,
                    double m10, double m11, double m12, double m13,
                    double m20, double m21, double m22, double m23,
                    double m30, double m31, double m32, double m33) {
        this.matrix = new double[4][4];
        this.matrix[0][0] = m00;
        this.matrix[0][1] = m01;
        this.matrix[0][2] = m02;
        this.matrix[0][3] = m03;

        this.matrix[1][0] = m10;
        this.matrix[1][1] = m11;
        this.matrix[1][2] = m12;
        this.matrix[1][3] = m13;

        this.matrix[2][0] = m20;
        this.matrix[2][1] = m21;
        this.matrix[2][2] = m22;
        this.matrix[2][3] = m23;

        this.matrix[3][0] = m30;
        this.matrix[3][1] = m31;
        this.matrix[3][2] = m32;
        this.matrix[3][3] = m33;
    }

    public Matrix4f(Matrix4f matrix4f) {
        this(matrix4f.matrix[0][0], matrix4f.matrix[0][1], matrix4f.matrix[0][2], matrix4f.matrix[0][3],
                matrix4f.matrix[1][0], matrix4f.matrix[1][1], matrix4f.matrix[1][2], matrix4f.matrix[1][3],
                matrix4f.matrix[2][0], matrix4f.matrix[2][1], matrix4f.matrix[2][2], matrix4f.matrix[2][3],
                matrix4f.matrix[3][0], matrix4f.matrix[3][1], matrix4f.matrix[3][2], matrix4f.matrix[3][3]);
    }

    public Matrix4f(double[] vertices) {
        if (vertices == null) {
            throw new IllegalStateException("v is null");
        }

        if (vertices.length < 16) {
            throw new IllegalArgumentException("Can not create a Matrix 4x4 with that length = " + vertices.length);
        }
        this.matrix = new double[4][4];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(vertices, i * 4, matrix[i], 0, matrix[i].length);
        }
    }

    public void mul(double scalar) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] *= scalar;
    }

    public void mul(Matrix4f m1) {
        var temp = new double[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int u = 0; u < matrix[i].length; u++) {
                for (int j = 0; j < matrix.length; j++) {
                    temp[i][u] += this.matrix[i][j] * m1.matrix[j][u];
                }
            }
        }

        this.matrix = temp;
    }

    public Vector3f multiplyByVector3(final Vector3f vertex) {
        final var x = (vertex.x * this.matrix[0][0]) + (vertex.y * this.matrix[0][1]) + (vertex.z * this.matrix[0][2]) + this.matrix[0][3];
        final var y = (vertex.x * this.matrix[1][0]) + (vertex.y * this.matrix[1][1]) + (vertex.z * this.matrix[1][2]) + this.matrix[1][3];
        final var z = (vertex.x * this.matrix[2][0]) + (vertex.y * this.matrix[2][1]) + (vertex.z * this.matrix[2][2]) + this.matrix[2][3];
        final var w = (vertex.x * this.matrix[3][0]) + (vertex.y * this.matrix[3][1]) + (vertex.z * this.matrix[3][2]) + this.matrix[3][3];
        // TODO z/w отдать
        return new Vector3f(x / w, y / w, z / w);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double get(int n, int m) {
        return matrix[n][m];
    }

    public void set(int n, int m, double value) {
        this.matrix[n][m] = value;
    }
}
