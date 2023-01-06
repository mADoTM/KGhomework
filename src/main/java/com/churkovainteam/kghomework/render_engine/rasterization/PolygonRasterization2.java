package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class PolygonRasterization2 {

    public static void drawPolygon(
            GraphicsContext graphicsContext, List<PolygonVertex> points, Color color, float[][] zBuffer,
            Vector3f position, Image picture, boolean usedTexture, boolean usedLighting
    ) {
        sortPoints(points);

        PolygonVertex firstPoint = points.get(0);
        PolygonVertex secondPoint = points.get(1);
        PolygonVertex thirdPoint = points.get(2);

        float xAxisIncrement12 = getXAxisIncrement(firstPoint, secondPoint);
        float xAxisIncrement13 = getXAxisIncrement(firstPoint, thirdPoint);
        float xAxisIncrement23 = getXAxisIncrement(secondPoint, thirdPoint);

        float leftBoard = firstPoint.getX();
        float rightBoard = leftBoard;

        float firstLeftIncrement, firstRightIncrement, secondLeftIncrement, secondRightIncrement;

        if (xAxisIncrement13 > xAxisIncrement12) {
            firstLeftIncrement = xAxisIncrement12;
            firstRightIncrement = secondRightIncrement = xAxisIncrement13;
            secondLeftIncrement = xAxisIncrement23;
        } else {
            firstLeftIncrement = secondLeftIncrement = xAxisIncrement13;
            firstRightIncrement = xAxisIncrement12;
            secondRightIncrement = xAxisIncrement23;
        }

        DrawingPartsPolygons.drawPartTriangle2(graphicsContext, firstPoint.getY(), secondPoint.getY() - 1,
                leftBoard, rightBoard, firstLeftIncrement, firstRightIncrement, firstPoint, secondPoint, thirdPoint,
                color, zBuffer, position, picture, usedTexture, usedLighting);

        if (firstPoint.getY() == secondPoint.getY()) {
            leftBoard = firstPoint.getX();
            rightBoard = secondPoint.getX();
            secondLeftIncrement = xAxisIncrement13;
            secondRightIncrement = xAxisIncrement23;
        } else {
            int height = secondPoint.getY() - firstPoint.getY();
            leftBoard += height * firstLeftIncrement;
            rightBoard += height * firstRightIncrement;
        }

        DrawingPartsPolygons.drawPartTriangle2(graphicsContext, secondPoint.getY(), thirdPoint.getY(), leftBoard,
                rightBoard, secondLeftIncrement, secondRightIncrement, firstPoint, secondPoint, thirdPoint, color,
                zBuffer, position, picture, usedTexture, usedLighting);
    }

    protected static void sortPoints(List<PolygonVertex> points) {
        points.sort((a, b) -> {
            if (a.getY() == b.getY()) {
                return a.getX() - b.getX();
            } else {
                return a.getY() - b.getY();
            }
        });
    }

    private static float getXAxisIncrement(PolygonVertex firstPont, PolygonVertex secondPoint) {
        if (firstPont.getY() == secondPoint.getY()) {
            return 0;
        } else  {
            float increment = secondPoint.getX() - firstPont.getX();
            increment /= secondPoint.getY() - firstPont.getY();
            return increment;
        }
    }
}
