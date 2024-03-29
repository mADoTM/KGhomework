package com.churkovainteam.kghomework.model;

import com.churkovainteam.kghomework.math.Matrix4f;
import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;

import java.util.List;

public class TransformedTriangulatedModel {
    private final TriangulatedModelWithCorrectNormal triangulatedModel;

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
        this.triangulatedModel = new TriangulatedModelWithCorrectNormal(model);
        this.translatedVector = new Vector3f();

        this.scaleX = 1;
        this.scaleY = 1;
        this.scaleZ = 1;
    }

    public String getModelName() {
        return triangulatedModel.getInitialModel().modelName;
    }

    public List<Polygon> getPolygons() {
        return triangulatedModel.getTriangulatedPolygons();
    }

    public List<Vector3f> getNormals() {
        return triangulatedModel.getInitialModel().normals;
    }

    public List<Vector2f> getTexture() {
        return triangulatedModel.getInitialModel().textureVertices;
    }
    
    public void setRotate(Vector3f rotateVector) {
        this.rotateAngleX = (float) Math.toRadians(rotateVector.x);
        this.rotateAngleY = (float) Math.toRadians(rotateVector.y);
        this.rotateAngleZ = (float) Math.toRadians(rotateVector.z);
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

        final var defaultVector = triangulatedModel
                .getInitialModel()
                .vertices
                .get(index);

        var transformedVector = new Vector3f(defaultVector.x, defaultVector.y, defaultVector.z);
        transformedVector.scaleX(scaleX);
        transformedVector.scaleY(scaleY);
        transformedVector.scaleZ(scaleZ);

        transformedVector = getRotationMatrix()
                .multiplyByVector3(transformedVector);

        transformedVector.add(translatedVector);

        return transformedVector;
    }

    public TriangulatedModelWithCorrectNormal getTriangulatedModel() {
        return triangulatedModel;
    }

    public Matrix4f getRotationMatrix() {
        return Matrix4f.rotationMatrix(rotateAngleX, rotateAngleY, rotateAngleZ);
    }
}
