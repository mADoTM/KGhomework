package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class PolygonRasterization {

    public static void drawPolygon(
            GraphicsContext graphicsContext, List<Vector3f> points, Color color, float[][] zBuffer
    ) {
        sortPoints(points);

        Vector3f firstPoint = points.get(0);
        Vector3f secondPoint = points.get(1);
        Vector3f thirdPoint = points.get(2);

        float xAxisIncrement12 = getXAxisIncrement(firstPoint, secondPoint);
        float xAxisIncrement13 = getXAxisIncrement(firstPoint, thirdPoint);

        int[] firstLeftBoard, firstRightBoard, secondLeftBoard, secondRightBoard;

        if (firstPoint.y != secondPoint.y) {
            Vector3f auxiliaryPoint = getAuxiliaryPoint(firstPoint, secondPoint, thirdPoint);

            if (xAxisIncrement13 > xAxisIncrement12) {
                firstLeftBoard = drawBresenhamLine(firstPoint, secondPoint, true);
                firstRightBoard = drawBresenhamLine(firstPoint, auxiliaryPoint, false);
                secondRightBoard = drawBresenhamLine(auxiliaryPoint, thirdPoint, false);
                secondLeftBoard = drawBresenhamLine(secondPoint, thirdPoint, true);
            } else {
                firstLeftBoard = drawBresenhamLine(firstPoint, auxiliaryPoint, true);
                secondLeftBoard = drawBresenhamLine(auxiliaryPoint, thirdPoint, true);
                firstRightBoard = drawBresenhamLine(firstPoint, secondPoint, false);
                secondRightBoard = drawBresenhamLine(secondPoint, thirdPoint, false);
            }

            drawPartTriangle(graphicsContext, (int) firstPoint.y, firstLeftBoard, firstRightBoard,
                    firstPoint, secondPoint, thirdPoint, color, zBuffer);
        } else {
            secondLeftBoard = drawBresenhamLine(firstPoint, thirdPoint, true);
            secondRightBoard = drawBresenhamLine(secondPoint, thirdPoint, false);
        }

        drawPartTriangle(graphicsContext, (int) secondPoint.y, secondLeftBoard, secondRightBoard,
                firstPoint, secondPoint, thirdPoint, color, zBuffer);
    }

    protected static void sortPoints(List<Vector3f> points) {
        points.sort((a, b) -> {
            if (a.y == b.y) {
                return (int) (a.x - b.x);
            } else {
                return (int) (a.y - b.y);
            }
        });
    }

    private static Vector3f getAuxiliaryPoint(Vector3f firstPoint, Vector3f secondPoint, Vector3f thirdPoint) {
        float dy12 = secondPoint.y - firstPoint.y;
        float dy13 = thirdPoint.y - firstPoint.y;
        float dx13 = thirdPoint.x - firstPoint.x;
        float x = dy12 * dx13 / dy13 + firstPoint.x;

        return new Vector3f(x, secondPoint.y, 0); // z пока просто так
    }

    private static float getXAxisIncrement(Vector3f firstPoint, Vector3f secondPoint) {
        if (firstPoint.y == secondPoint.y) {
            return 0;
        } else {
            float increment = secondPoint.x - firstPoint.x;
            increment /= secondPoint.y - firstPoint.y;
            return increment;
        }
    }

    private static int[] drawBresenhamLine(Vector3f firstPoint, Vector3f secondPoint, boolean isLeftBoard) {
        int x, y, deltaX, deltaY, additionX, additionY, incrementX, incrementY, error, errorIncrease, errorDecrease;
        int initialHeight = (int) firstPoint.y;

        deltaX = (int) (secondPoint.x - firstPoint.x);
        deltaY = (int) (secondPoint.y - initialHeight);

        int[] array = new int[deltaY + 1];
        Arrays.fill(array, -1);

        incrementX = Integer.compare(deltaX, 0);
        incrementY = Integer.compare(deltaY, 0);

        deltaX = Math.abs(deltaX);
        deltaY = Math.abs(deltaY);

        if (deltaX > deltaY) {
            additionX = incrementX;
            additionY = 0;
            errorIncrease = deltaX;
            errorDecrease = deltaY;
        } else {
            additionX = 0;
            additionY = incrementY;
            errorIncrease = deltaY;
            errorDecrease = deltaX;
        }

        error = errorIncrease / 2;
        x = (int) firstPoint.x;
        y = initialHeight;
        array[0] = x;

        for (int i = 0; i < errorIncrease; i++) {
            error -= errorDecrease;

            if (error < 0) {
                error += errorIncrease;
                x += incrementX;
                y += incrementY;
            } else {
                x += additionX;
                y += additionY;
            }

            int index = y - initialHeight;

            if (array[index] != -1) {
                if (array[index] > x && isLeftBoard) { // для левой
                    array[index] = x;
                }
            } else {
                array[index] = x;
            }
        }

        return array;
    }

    private static void drawPartTriangle(
            GraphicsContext graphicsContext, int startY, int[] leftArray, int[] rightArray,
            Vector3f firstPoint, Vector3f secondPoint, Vector3f thirdPoint, Color color, float[][] zBuffer
    ) {
        for (int i = 0; i < leftArray.length; i++) {
            int leftBoard = leftArray[i];
            int rightBoard = rightArray[i];
            int y = i + startY;

            for (int x = leftBoard; x <= rightBoard; x++) {
                float z = BarycentricUtilities.getZ(x, y, firstPoint, secondPoint, thirdPoint);

                if (zBuffer[y][x] == 0 || zBuffer[y][x] > z) {
                    zBuffer[y][x] = z;
                    graphicsContext.getPixelWriter().setColor(x, y, color);
                }
            }
        }
    }
}