package com.churkovainteam.kghomework.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Polygon {
    private List<Integer> vertexIndices;
    private List<Integer> textureVertexIndices;
    private List<Integer> normalIndices;


    public Polygon() {
        vertexIndices = new ArrayList<>();
        textureVertexIndices = new ArrayList<>();
        normalIndices = new ArrayList<>();
    }

    public void setVertexIndices(List<Integer> vertexIndices) {
        if (vertexIndices.size() < 3) {
            throw new IllegalArgumentException("Illegal number of polygon vertices: " + vertexIndices.size() + ".");
        }

        this.vertexIndices = vertexIndices;
    }

    public void setTextureVertexIndices(List<Integer> textureVertexIndices) {
        if (vertexIndices.size() < 3) {
            throw new IllegalArgumentException("Illegal number of polygon texture vertices: " + textureVertexIndices.size() + ".");
        }

        this.textureVertexIndices = textureVertexIndices;
    }

    public void setNormalIndices(List<Integer> normalIndices) {
        if (vertexIndices.size() < 3) {
            throw new IllegalArgumentException("Illegal number of normals: " + normalIndices.size() + ".");
        }

        this.normalIndices = normalIndices;
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public List<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public List<Integer> getNormalIndices() {
        return normalIndices;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("f ");

        for (int index : vertexIndices) {
            string.append(index);
            string.append(" ");
        }
        return string.toString();
    }
}

