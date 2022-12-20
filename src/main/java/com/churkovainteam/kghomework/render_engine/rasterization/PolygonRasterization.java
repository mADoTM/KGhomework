package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.MathSettings;
import com.churkovainteam.kghomework.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public class PolygonRasterization {

    public static void drawPolygon(
            GraphicsContext graphicsContext, List<Vector3f> points, Color color, float[][] zBuffer
    ) {
        sortPoints(points);

        Vector3f firstPoint = points.get(0);
        Vector3f secondPoint = points.get(1);
        Vector3f thirdPoint = points.get(2);

//        System.out.println("min x - " + points.stream().min(Comparator.comparing(x -> x.x)));
//        System.out.println("min x - " + points.stream().max(Comparator.comparing(x -> x.x)));

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
//        System.out.println("(secondPoint.y - initialHeight)" + (secondPoint.y + " " + initialHeight));
        //log.println(deltaY);
        int[] array = new int[0];
        try {

            array = new int[deltaY + 1];
        }
        catch (OutOfMemoryError e) {
            System.out.println("outOfMemory " + deltaY);
        }
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

//        System.out.println("array lenght " + array.length);
        return array;
    }

    private static final PrintWriter log;

    static {
        try {
            log = new PrintWriter(".\\my_log.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void drawPartTriangle(
            GraphicsContext graphicsContext, int startY, int[] leftArray, int[] rightArray,
            Vector3f firstPoint, Vector3f secondPoint, Vector3f thirdPoint, Color color, float[][] zBuffer
    ) {
        for (int i = 0; i < leftArray.length; i++) {
            int leftBoard = leftArray[i];
            int rightBoard = rightArray[i];
            //log.println(leftBoard + " " + rightBoard);
            int y = i + startY;

            if (y < 0 || y >= zBuffer.length) {
                continue;
            }

            for (int x = leftBoard; x <= rightBoard; x++) {
                if (x < 0 || x >= zBuffer[0].length) {
                    continue;
                }

                float z = BarycentricUtilities.getZ(x, y, firstPoint, secondPoint, thirdPoint);
                //System.out.println(firstPoint + " " + secondPoint + " " + thirdPoint);
                if(z < 0 || z > 1) {
                    continue;
                }

                if (zBuffer[y][x] == 0 || zBuffer[y][x] > z) {
                    zBuffer[y][x] = z;
                    graphicsContext.getPixelWriter().setColor(x, y, color);
                }
            }
        }
    }
}