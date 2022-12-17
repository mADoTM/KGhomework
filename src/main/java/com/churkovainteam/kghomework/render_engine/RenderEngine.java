package com.churkovainteam.kghomework.render_engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.churkovainteam.kghomework.math.Matrix4f;
import com.churkovainteam.kghomework.math.Point2f;
import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.Model;
import com.churkovainteam.kghomework.model.Polygon;
import com.churkovainteam.kghomework.render_engine.rasterization.BarycentricUtilities;
import com.churkovainteam.kghomework.render_engine.rasterization.PolygonRasterization;
import com.churkovainteam.kghomework.model.TransformedTriangulatedModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderEngine {
    // ChangedModel, углы, масштабирование, перемещение, Model.
    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final TransformedTriangulatedModel mesh,
            final int width,
            final int height) {
        final var viewMatrix = camera.getViewMatrix();
        final var projectionMatrix = camera.getProjectionMatrix();

        final var modelViewProjectionMatrix = new Matrix4f(projectionMatrix);
        modelViewProjectionMatrix.mul(viewMatrix);
        modelViewProjectionMatrix.mul(Matrix4f.identityMatrix());

        float[][] zBuffer = new float[height][width];
        final int nPolygons = mesh.getPolygons().size();
        
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh
                    .getPolygons()
                    .get(polygonInd)
                    .getVertexIndices()
                    .size();

            final var resultPoints = new ArrayList<Point2f>();
            List<Vector3f> resultPointsWithZ = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                final var vertex = mesh.getVertices().get(mesh
                        .getPolygons()
                        .get(polygonInd)
                        .getVertexIndices()
                        .get(vertexInPolygonInd));

                Vector3f rotatedPoint = multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertex);

                final var resultPoint = vertexToPoint(rotatedPoint, width, height);
                resultPoints.add(resultPoint);
                resultPointsWithZ.add(new Vector3f((int) resultPoint.x,(int) resultPoint.y, rotatedPoint.z));
                // Здесь мне нужно приведение к int, т.к. растеризация была написана для целых x и y, чтобы не было
                // никаких мили выходов за края полигонов, которые могут возникнуть из-за float
            }


            PolygonRasterization.drawPolygon(graphicsContext, resultPointsWithZ, Color.PURPLE, zBuffer);


            //рисование сетки как и было
            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).x,
                        resultPoints.get(vertexInPolygonInd - 1).y,
                        resultPoints.get(vertexInPolygonInd).x,
                        resultPoints.get(vertexInPolygonInd).y);
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).x,
                        resultPoints.get(nVerticesInPolygon - 1).y,
                        resultPoints.get(0).x,
                        resultPoints.get(0).y);


        }
    }

//    private static void drawLine(
//            GraphicsContext graphicsContext, Vector3f firstPoint,Vector3f secondPoint, boolean isLeftBoard
//    ) {
//        int x, y, deltaX, deltaY, additionX, additionY, incrementX, incrementY, error, errorIncrease, errorDecrease;
//        int initialHeight = (int) firstPoint.y;
//
//        deltaX = (int) (secondPoint.x - firstPoint.x);
//        deltaY = (int) (secondPoint.y - initialHeight);
//
//        incrementX = Integer.compare(deltaX, 0);
//        incrementY = Integer.compare(deltaY, 0);
//
//        deltaX = Math.abs(deltaX);
//        deltaY = Math.abs(deltaY);
//
//        if (deltaX > deltaY) {
//            additionX = incrementX;
//            additionY = 0;
//            errorIncrease = deltaX;
//            errorDecrease = deltaY;
//        } else {
//            additionX = 0;
//            additionY = incrementY;
//            errorIncrease = deltaY;
//            errorDecrease = deltaX;
//        }
//
//        error = errorIncrease / 2;
//        x = (int) firstPoint.x;
//        y = initialHeight;
//
//        for (int i = 0; i < errorIncrease; i++) {
//            error -= errorDecrease;
//
//            if (error < 0) {
//                error += errorIncrease;
//                x += incrementX;
//                y += incrementY;
//            } else {
//                x += additionX;
//                y += additionY;
//            }
//
//            float z = BarycentricUtilities.getZ(x, y, firstPoint, secondPoint, thirdPoint);
//
//            if (zBuffer[y][x] == 0 || zBuffer[y][x] > z) {
//                zBuffer[y][x] = z;
//                graphicsContext.getPixelWriter().setColor(x, y, color);
//            }
//        }
//    }
}