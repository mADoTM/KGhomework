package com.churkovainteam.kghomework.render_engine;

import java.util.ArrayList;
import java.util.List;

import com.churkovainteam.kghomework.math.Matrix4f;
import com.churkovainteam.kghomework.math.Point2f;
import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.Polygon;
import com.churkovainteam.kghomework.render_engine.rasterization.DrawingPartsPolygons;
import com.churkovainteam.kghomework.model.TransformedTriangulatedModel;

import com.churkovainteam.kghomework.render_engine.rasterization.PolygonRasterization;
import com.churkovainteam.kghomework.render_engine.rasterization.PolygonRasterization2;
import com.churkovainteam.kghomework.render_engine.rasterization.PolygonVertex;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.churkovainteam.kghomework.math.Point2f.vertexToPoint;

public class RenderEngine {
    // ChangedModel, углы, масштабирование, перемещение, Model.
    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final TransformedTriangulatedModel mesh,
            final int width,
            final int height,
            float[][] zBuffer,
            final Image picture,
            boolean usedPolygonalGrid,
            boolean usedTexture,
            boolean usedLighting) {
        final var viewMatrix = camera.getViewMatrix();
        final var projectionMatrix = camera.getProjectionMatrix();

        final var modelViewProjectionMatrix = new Matrix4f(projectionMatrix);
        modelViewProjectionMatrix.mul(viewMatrix);
        modelViewProjectionMatrix.mul(Matrix4f.identityMatrix());

        final int nPolygons = mesh.getPolygons().size();

        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            Polygon currentPolygon = mesh
                    .getPolygons()
                    .get(polygonInd);

            final int nVerticesInPolygon = currentPolygon
                    .getVertexIndices()
                    .size();

//            final var resultPoints = new ArrayList<Point2f>();
            List<PolygonVertex> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                int currentVertexIndex = currentPolygon
                        .getVertexIndices()
                        .get(vertexInPolygonInd);

                int textureIndex = currentPolygon.getTextureVertexIndices().get(vertexInPolygonInd);
                Vector3f normal = mesh.getNormals().get(currentVertexIndex);

                final var vertex = mesh.getVertices().get(currentVertexIndex);
                Vector3f rotatedPoint = modelViewProjectionMatrix.multiplyByVector3(vertex);

                final var resultPoint = vertexToPoint(rotatedPoint, width, height);
//                nullVector(resultPoint, width, height);
//                resultPoints.add(resultPoint);
                resultPoints.add(new PolygonVertex((int) resultPoint.x, (int) resultPoint.y, rotatedPoint.z,
                        mesh.getTexture().get(textureIndex), normal, vertex));
                // Здесь мне нужно приведение к int, т.к. растеризация была написана для целых x и y, чтобы не было
                // никаких мили выходов за края полигонов, которые могут возникнуть из-за float
            }

            PolygonRasterization.drawPolygon(graphicsContext, resultPoints, Color.RED, zBuffer,
                    camera.getPosition(), picture, usedTexture, usedLighting);

            //это такая же отрисовка только через DDA, но лучше использовать брезенхема. Если всё-таки захотите
            //проверить с ней, то раскомментируйте строки 80 81 и закомментируйте 75 76
//            PolygonRasterization2.drawPolygon(graphicsContext, resultPoints, Color.RED, zBuffer,
//                    camera.getPosition(), picture, usedTexture, usedLighting);

            //рисование сетки как и было
//            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
//                graphicsContext.strokeLine(
//                        resultPoints.get(vertexInPolygonInd - 1).x,
//                        resultPoints.get(vertexInPolygonInd - 1).y,
//                        resultPoints.get(vertexInPolygonInd).x,
//                        resultPoints.get(vertexInPolygonInd).y);
//            }
//
//            if (nVerticesInPolygon > 0) {
//                graphicsContext.strokeLine(
//                        resultPoints.get(nVerticesInPolygon - 1).x,
//                        resultPoints.get(nVerticesInPolygon - 1).y,
//                        resultPoints.get(0).x,
//                        resultPoints.get(0).y);
//            }


            if (usedPolygonalGrid) {
                for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                    DrawingPartsPolygons.drawLine(graphicsContext, resultPoints.get(vertexInPolygonInd - 1),
                            resultPoints.get(vertexInPolygonInd), Color.BLACK, zBuffer);
                }

                if (nVerticesInPolygon > 0) {
                    DrawingPartsPolygons.drawLine(graphicsContext, resultPoints.get(nVerticesInPolygon - 1),
                            resultPoints.get(0), Color.BLACK, zBuffer);
                }
            }
        }
    }

    private static void nullVector(Point2f vector3f, int width, int height) {
        if (vector3f.x < 0)
            vector3f.x = 0;
        if (vector3f.x >= width) {
            vector3f.x = width - 1;
        }
        if (vector3f.y < 0)
            vector3f.y = 0;
        if (vector3f.y >= height) {
            vector3f.y = height - 1;
        }
    }
}