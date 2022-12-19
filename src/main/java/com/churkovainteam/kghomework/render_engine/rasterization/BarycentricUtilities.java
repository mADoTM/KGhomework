package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.Vector3f;

public class BarycentricUtilities {
    public static float getZ(
            int currentX, int currentY, Vector3f firstPoint, Vector3f secondPoint, Vector3f thirdPoint
    ) {
        float alpha, beta;

        float numerator = firstPoint.x * (currentY - thirdPoint.y) +
                currentX * (thirdPoint.y - firstPoint.y) + thirdPoint.x * (firstPoint.y - currentY);

        float denominator = firstPoint.x * (secondPoint.y - thirdPoint.y) +
                secondPoint.x * (thirdPoint.y - firstPoint.y) + thirdPoint.x *
                (firstPoint.y - secondPoint.y);

        beta = numerator / denominator;

        if (firstPoint.x == thirdPoint.x) {
            alpha = (currentY - thirdPoint.y + beta * (thirdPoint.y - secondPoint.y)) /
                    (firstPoint.y - thirdPoint.y);
        } else {
            alpha = (currentX - thirdPoint.x - beta * (secondPoint.x - thirdPoint.x)) /
                    (firstPoint.x - thirdPoint.x);
        }

        return alpha * firstPoint.z + beta * secondPoint.z + (1 - alpha - beta) * thirdPoint.z;
    }
}
