package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DrawingPartsPolygons {
    public static void drawLine(
            GraphicsContext graphicsContext, PolygonVertex firstPoint, PolygonVertex secondPoint, Color color,
            float[][] zBuffer
    ) {
        int x, y, deltaX, deltaY, additionX, additionY, incrementX, incrementY, error, errorIncrease, errorDecrease;
        int initialHeight = firstPoint.getY();

        deltaX = secondPoint.getX() - firstPoint.getX();
        deltaY = secondPoint.getY() - initialHeight;

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
        x = firstPoint.getX();
        y = initialHeight;


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

            if (y >= 0 && y < zBuffer.length && x >= 0 && x < zBuffer[0].length) {
                zBuffer[y][x] = -1;

                graphicsContext.getPixelWriter().setColor(x, y, color);
            }
        }
    }

    public static void drawPartTriangle(
            GraphicsContext graphicsContext, int startY, int[] leftArray, int[] rightArray, PolygonVertex firstPoint,
            PolygonVertex secondPoint, PolygonVertex thirdPoint, Color color, float[][] zBuffer, Vector3f position,
            Image picture, boolean usedTexture, boolean usedLighting
    ) {
        Color preColor = color;
        for (int i = 0; i < leftArray.length; i++) {
            int leftBoard = leftArray[i];
            int rightBoard = rightArray[i];
            int y = i + startY;

            if (y < 0 || y >= zBuffer.length) {
                continue;
            }

            for (int x = leftBoard; x <= rightBoard; x++) {
                if (x < 0 || x >= zBuffer[0].length) {
                    continue;
                }

                float z = BarycentricUtilities.getZ(x, y, firstPoint, secondPoint, thirdPoint);
                if (z < 0 || z > 1) {
                    continue;
                }

                if (zBuffer[y][x] == 0 || zBuffer[y][x] > z) {
                    zBuffer[y][x] = z;

                    if (usedTexture) {
                        preColor = getTexturePixelColor(firstPoint, secondPoint, thirdPoint, x, y, picture);
                    }

                    if (usedLighting) {
                        preColor = getIlluminatedColor(firstPoint, secondPoint, thirdPoint, color, position, x, y);
                    }

                    graphicsContext.getPixelWriter().setColor(x, y, preColor);
                }
            }
        }
    }

    private static Color getIlluminatedColor(
            PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint, Color color, Vector3f position,
            int x, int y
    ) {
        Vector3f worldCoordinates = BarycentricUtilities.getWorldCoordinates(x, y, firstPoint, secondPoint, thirdPoint);
        Vector3f cameraVector = Vector3f.fromTwoPoints(worldCoordinates, position);
        cameraVector.normalize();

        Vector3f normal = BarycentricUtilities.getNormal(x, y, firstPoint, secondPoint, thirdPoint);
        normal.normalize();


        float ratio = cameraVector.dot(normal);

        if (ratio < 0) {
            ratio = 0;
        }
        ratio /= 2;
        ratio += 0.37F;

        float r = (float) (ratio * color.getRed());
        float g = (float) (ratio * color.getGreen());
        float b = (float) (ratio * color.getBlue());

        return new Color(r, g, b, 1.0);
    }

    private static Color getTexturePixelColor(
            PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint, int x, int y, Image picture
    ) {
        Vector2f currentTexture = BarycentricUtilities.getTexture(x, y, firstPoint, secondPoint, thirdPoint);


        int width = (int) picture.getWidth();
        int height = (int) picture.getHeight();

        currentTexture.x *= width;
        currentTexture.y *= height;

        currentTexture.x = ((int) currentTexture.x >= width) ? width - 1 : currentTexture.x;
        currentTexture.y = ((int) currentTexture.y >= height) ? height - 1 : currentTexture.y;

        currentTexture.x = ((int) currentTexture.x <= 0) ? 0 : currentTexture.x;
        currentTexture.y = ((int) currentTexture.y <= 0) ? 0 : currentTexture.y;


        return picture.getPixelReader().getColor((int) currentTexture.x, (int) currentTexture.y);
    }


//    private static boolean checkPixelForCorrectness(int x, int y, float z, float[][] zBuffer) {
//        return y >= 0 && y < zBuffer.length &&
//                x >= 0 && x < zBuffer[0].length &&
//                z >= 0 && z <= 1 && (zBuffer[y][x] == 0 || zBuffer[y][x] > z);
//    }
//    public static void drawPoint(
//            GraphicsContext graphicsContext, PolygonVertex point, Color color, float[][] zBuffer
//    ) {
//        if (checkPixelForCorrectness(point.getX(), point.getY(), point.getZ(), zBuffer)) {
//            zBuffer[point.getY()][point.getX()] = point.getZ();
//
//            graphicsContext.getPixelWriter().setColor(point.getX(), point.getY(), color);
//        }
//    }
//
//    private static float getZForLine(PolygonVertex firstPoint, PolygonVertex secondPoint, int x, int y) {
//        float lineLength = getLineLength(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
//        float partLineLength = getLineLength(firstPoint.getX(), firstPoint.getY(), x, y);
//
//        return firstPoint.getZ() + (secondPoint.getZ() - firstPoint.getZ()) * partLineLength / lineLength;
//    }
//
//    private static float getLineLength(int startX, int startY, int endX, int endY) {
//        return (float) Math.pow(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2), 0.5);
//    }
}
