package com.churkovainteam.kghomework.render_engine;

import com.churkovainteam.kghomework.math.Matrix4f;
import com.churkovainteam.kghomework.math.Point2f;
import com.churkovainteam.kghomework.math.Vector3f;

public class GraphicConveyor {

    public static Matrix4f rotateScaleTranslate() {
        float[] matrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1};
        return new Matrix4f(matrix);
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target) {
        return lookAt(eye, target, new Vector3f(0, 1.0F, 0));
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target, Vector3f up) {
        final var resultX = new Vector3f();
        final var resultY = new Vector3f();
        final var resultZ = new Vector3f();

        resultZ.sub(target, eye);
        resultX.cross(up, resultZ);
        resultY.cross(resultZ, resultX);

        resultX.normalize();
        resultY.normalize();
        resultZ.normalize();

        float[] matrix = new float[]{
                resultX.x, resultX.y, resultX.z, -resultX.dot(eye),
                resultY.x, resultY.y, resultY.z, -resultY.dot(eye),
                resultZ.x, resultZ.y, resultZ.z, -resultZ.dot(eye),
                0, 0, 0, 1};
        return new Matrix4f(matrix);
    }

    public static Matrix4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        final var result = new Matrix4f();
        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov * 0.5F)));
        result.m00 = tangentMinusOnDegree / aspectRatio;
        result.m11 = tangentMinusOnDegree;
        result.m22 = (farPlane + nearPlane) / (farPlane - nearPlane);
        result.m32 = 1.0F;
        result.m23 = 2 * (nearPlane * farPlane) / (nearPlane - farPlane);
        return result;
    }

    public static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix, final Vector3f vertex) {
        final var x = (vertex.x * matrix.m00) + (vertex.y * matrix.m01) + (vertex.z * matrix.m02) + matrix.m03;
        final var y = (vertex.x * matrix.m10) + (vertex.y * matrix.m11) + (vertex.z * matrix.m12) + matrix.m13;
        final var z = (vertex.x * matrix.m20) + (vertex.y * matrix.m21) + (vertex.z * matrix.m22) + matrix.m23;
        final var w = (vertex.x * matrix.m30) + (vertex.y * matrix.m31) + (vertex.z * matrix.m32) + matrix.m33;
        return new Vector3f(x / w, y / w, z / w);
    }

    public static Point2f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.x * width + width / 2.0F, -vertex.y * height + height / 2.0F);
    }
}
