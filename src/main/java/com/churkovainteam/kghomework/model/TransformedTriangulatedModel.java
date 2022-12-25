package com.churkovainteam.kghomework.model;

import com.churkovainteam.kghomework.math.Vector3f;
import java.util.List;

public class TransformedTriangulatedModel {
    private final TriangulationModel triangulationModel;

    private Vector3f translatedVector;

    private float rotateAngleX;
    private float rotateAngleY;
    private float rotateAngleZ;

    private float scaleX;
    private float scaleY;
    private float scaleZ;

    public TransformedTriangulatedModel(Model model) {
        if (model == null) {
            throw new IllegalArgumentException("Can't create a transformed model, because source is null");
        }
        this.triangulationModel = new TriangulationModel(model);
        this.translatedVector = new Vector3f();

        this.scaleX = 1;
        this.scaleY = 1;
        this.scaleZ = 1;
    }

    public List<Polygon> getPolygons() {
        return triangulationModel.getTriangulationPolygons();
    }

    public void setRotate(Vector3f rotateVector) {
        this.rotateAngleX = rotateVector.x;
        this.rotateAngleY = rotateVector.y;
        this.rotateAngleZ = rotateVector.z;
    }

    public void setScale(Vector3f scaleVector) {
        this.scaleX = scaleVector.x;
        this.scaleY = scaleVector.y;
        this.scaleZ = scaleVector.z;
    }

    public void setTranslatedVector(Vector3f translatedVector) {
        this.translatedVector = translatedVector;
    }

    public Vector3f getTransformedVector(int index) {

        final var defaultVector = triangulationModel
                .getInitialModel()
                .vertices
                .get(index);

        final var transformedVector = new Vector3f(defaultVector.x, defaultVector.y, defaultVector.z);
        transformedVector.scaleX(scaleX);
        transformedVector.scaleY(scaleY);
        transformedVector.scaleZ(scaleZ);

        transformedVector.rotateAroundX(rotateAngleX);
        transformedVector.rotateAroundY(rotateAngleY);
        transformedVector.rotateAroundZ(rotateAngleZ);

        transformedVector.add(translatedVector);

        return transformedVector;
    }
}
