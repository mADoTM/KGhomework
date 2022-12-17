package com.churkovainteam.kghomework.render_engine;

import java.util.ArrayList;

import com.churkovainteam.kghomework.math.Matrix4f;
import com.churkovainteam.kghomework.math.Point2f;
import com.churkovainteam.kghomework.model.TransformedTriangulatedModel;
import javafx.scene.canvas.GraphicsContext;

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

        final int nPolygons = mesh.getPolygons().size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh
                    .getPolygons()
                    .get(polygonInd)
                    .getVertexIndices()
                    .size();

            final var resultPoints = new ArrayList<Point2f>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                final var vertex = mesh.getVertices().get(mesh
                        .getPolygons()
                        .get(polygonInd)
                        .getVertexIndices()
                        .get(vertexInPolygonInd));

                final var resultPoint = Point2f
                        .vertexToPoint(modelViewProjectionMatrix.multiplyByVector3(vertex), width, height);
                resultPoints.add(resultPoint);
            }

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


}