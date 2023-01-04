package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.MathSettings;
import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;

public class BarycentricUtilities {
    public static float getZ(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint
    ) {
        float beta = calculateBeta(currentX, currentY, firstPoint, secondPoint, thirdPoint);
        float alpha = calculateAlpha(currentX, currentY, firstPoint, secondPoint, thirdPoint, beta);


        if (Float.isNaN(alpha) || Float.isNaN(beta)) {
            System.out.println(currentX + " " + currentY);
            System.out.println(beta + " " + alpha + " это getZ");
        }

        return alpha * firstPoint.getZ() + beta * secondPoint.getZ() + (1 - alpha - beta) * thirdPoint.getZ();
    }

    public static Vector3f getNormal(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint
    ) {
        float beta = calculateBeta(currentX, currentY, firstPoint, secondPoint, thirdPoint);
        float alpha = calculateAlpha(currentX, currentY, firstPoint, secondPoint, thirdPoint, beta);


        if (Float.isNaN(alpha) || Float.isNaN(beta)) {
            System.out.println(beta + " " + alpha + " это getNormal");
        }

        Vector3f firstNormal = firstPoint.getNormal();
        Vector3f secondNormal = secondPoint.getNormal();
        Vector3f thirdNormal = thirdPoint.getNormal();


        float x =  alpha * firstNormal.x + beta * secondNormal.x + (1 - alpha - beta) * thirdNormal.x;
        float y =  alpha * firstNormal.y + beta * secondNormal.y + (1 - alpha - beta) * thirdNormal.y;
        float z = alpha * firstNormal.z + beta * secondNormal.z + (1 - alpha - beta) * thirdNormal.z;

        return new Vector3f(x, y, z);
    }

    public static Vector3f getWorldCoordinates(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint
    ) {
        float beta = calculateBeta(currentX, currentY, firstPoint, secondPoint, thirdPoint);
        float alpha = calculateAlpha(currentX, currentY, firstPoint, secondPoint, thirdPoint, beta);


        if (Float.isNaN(alpha) || Float.isNaN(beta)) {
            System.out.println(beta + " " + alpha + " это getWorldCoordinates");
        }

        Vector3f firstWorldCoordinates = firstPoint.getWorldCoordinates();
        Vector3f secondWorldCoordinates = secondPoint.getWorldCoordinates();
        Vector3f thirdWorldCoordinates = thirdPoint.getWorldCoordinates();


        float x =  alpha * firstWorldCoordinates.x + beta * secondWorldCoordinates.x + (1 - alpha - beta) * thirdWorldCoordinates.x;
        float y =  alpha * firstWorldCoordinates.y + beta * secondWorldCoordinates.y + (1 - alpha - beta) * thirdWorldCoordinates.y;
        float z = alpha * firstWorldCoordinates.z + beta * secondWorldCoordinates.z + (1 - alpha - beta) * thirdWorldCoordinates.z;

        return new Vector3f(x, y, z);
    }

    public static Vector2f getTexture(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint
    ) {
        float beta = calculateBeta(currentX, currentY, firstPoint, secondPoint, thirdPoint);
        float alpha = calculateAlpha(currentX, currentY, firstPoint, secondPoint, thirdPoint, beta);


        if (Float.isNaN(alpha) || Float.isNaN(beta)) {
            System.out.println(beta + " " + alpha + " это getTexture");
        }

        Vector2f firstTexture = firstPoint.getTexture();
        Vector2f secondTexture = secondPoint.getTexture();
        Vector2f thirdTexture = thirdPoint.getTexture();

        //System.out.println(firstTexture + "; " + secondTexture + "; " + thirdTexture);

        float x =  alpha * firstTexture.x + beta * secondTexture.x + (1 - alpha - beta) * thirdTexture.x;
        float y =  alpha * firstTexture.y + beta * secondTexture.y + (1 - alpha - beta) * thirdTexture.y;
        //System.out.println(x + " " + y);
        return new Vector2f(x, y);
    }

    private static float calculateBeta(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint
    ) {
        float numerator = firstPoint.getX() * (currentY - thirdPoint.getY()) +
                currentX * (thirdPoint.getY() - firstPoint.getY()) + thirdPoint.getX() * (firstPoint.getY() - currentY);

        if ((firstPoint.getX() == 0 || secondPoint.getY() == thirdPoint.getY()) &&
                (secondPoint.getX() == 0 || thirdPoint.getY() == firstPoint.getY()) &&
                (thirdPoint.getX() == 0 || firstPoint.getY() == secondPoint.getY())) {

            return 0;
        } else {
            if (MathSettings.isEqual(numerator, 0))
                return  0;

            float denominator = firstPoint.getX() * (secondPoint.getY() - thirdPoint.getY()) +
                    secondPoint.getX() * (thirdPoint.getY() - firstPoint.getY()) +
                    thirdPoint.getX() * (firstPoint.getY() - secondPoint.getY());

            //            if (Float.isInfinite(beta)) {
//                return  0;
//            }

            return numerator / denominator;
        }
    }

    private static float calculateAlpha(
            int currentX, int currentY, PolygonVertex firstPoint, PolygonVertex secondPoint, PolygonVertex thirdPoint,
            float beta
    ) {
        if (firstPoint.getX() == thirdPoint.getX() &&
                firstPoint.getY() == thirdPoint.getY()) {
            return  0;
        } else {

            if (firstPoint.getX() == thirdPoint.getX()) {
                return (currentY - thirdPoint.getY() + beta * (thirdPoint.getY() - secondPoint.getY())) /
                        (firstPoint.getY() - thirdPoint.getY());
            } else {
                return (currentX - thirdPoint.getX() - beta * (secondPoint.getX() - thirdPoint.getX())) /
                        (firstPoint.getX() - thirdPoint.getX());
            }
        }
    }
}
