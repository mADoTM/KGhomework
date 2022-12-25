package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.MathSettings;
import com.churkovainteam.kghomework.math.Vector3f;

public class BarycentricUtilities {
    public static float getZ(
            int currentX, int currentY, Vector3f firstPoint, Vector3f secondPoint, Vector3f thirdPoint
    ) {
        float alpha = 0, beta = 0, denominator = 0;

        float numerator = firstPoint.x * (currentY - thirdPoint.y) +
                currentX * (thirdPoint.y - firstPoint.y) + thirdPoint.x * (firstPoint.y - currentY);

        if ((MathSettings.isEqual(firstPoint.x, 0) || MathSettings.isEqual(secondPoint.y, thirdPoint.y)) &&
                (MathSettings.isEqual(secondPoint.x, 0) || MathSettings.isEqual(thirdPoint.y, firstPoint.y)) &&
                (MathSettings.isEqual(thirdPoint.x, 0) || MathSettings.isEqual(firstPoint.y, secondPoint.y))) {

            beta = 0;
        } else {
            denominator = firstPoint.x * (secondPoint.y - thirdPoint.y) +
                    secondPoint.x * (thirdPoint.y - firstPoint.y) +
                    thirdPoint.x * (firstPoint.y - secondPoint.y);

            beta = numerator / denominator;
            if (MathSettings.isEqual(numerator, 0))
                beta = 0;
            else if (MathSettings.isEqual(denominator, 0))
                beta = 1;
        }

        if (MathSettings.isEqual(firstPoint.x, thirdPoint.x)
                && MathSettings.isEqual(firstPoint.y, thirdPoint.y)) {
            alpha = 0;
        } else {

            if (MathSettings.isEqual(firstPoint.x, thirdPoint.x)) {
                alpha = (currentY - thirdPoint.y + beta * (thirdPoint.y - secondPoint.y)) /
                        (firstPoint.y - thirdPoint.y);
            } else {
                alpha = (currentX - thirdPoint.x - beta * (secondPoint.x - thirdPoint.x)) /
                        (firstPoint.x - thirdPoint.x);
            }
        }
        return alpha * firstPoint.z + beta * secondPoint.z + (1 - alpha - beta) * thirdPoint.z;
    }
}
