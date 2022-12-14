package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.model.Polygon;

import java.util.*;

public class RenderingPreparationUtilities {
    public static List<Polygon> triangulation(Polygon polygon) {
        List<Polygon> triangularPolygons = new ArrayList<>();

        List<Integer> vertexIndices = polygon.getVertexIndices();
        int quantityVertexes = vertexIndices.size();

        List<Integer> textureVertexIndices = polygon.getTextureVertexIndices();
        checkForCorrectListSize(textureVertexIndices, quantityVertexes, "текстурных координат");

        List<Integer> normalIndices = polygon.getNormalIndices();
        checkForCorrectListSize(normalIndices, quantityVertexes, "нормалей");


        for (int index = 1; index < vertexIndices.size() - 1; index++) {
            List<Integer> threeVertexIndices = getIndicesListForCurrentPolygon(vertexIndices, index);
            List<Integer> threeTextureVertexIndices = getIndicesListForCurrentPolygon(textureVertexIndices, index);
            List<Integer> threeNormalIndices = getIndicesListForCurrentPolygon(normalIndices, index);

            Polygon triangularPolygon = new Polygon();
            triangularPolygon.setVertexIndices(threeVertexIndices);
            triangularPolygon.setTextureVertexIndices(threeTextureVertexIndices);
            triangularPolygon.setNormalIndices(threeNormalIndices);

            triangularPolygons.add(triangularPolygon);
        }

        return triangularPolygons;
    }

    private static void checkForCorrectListSize(List<Integer> list, int expectedSize, String listName) {
        if (list.size() != 0 && list.size() != expectedSize) {
            throw new IllegalArgumentException("Некорректное количество " + listName + " в полигоне");
        }
    }

    private static List<Integer> getIndicesListForCurrentPolygon(List<Integer> list, int indexSecondVertex) {
        List<Integer> indices = new ArrayList<>();

        if (list.size() != 0) {
            indices.add(list.get(0));
            indices.add(list.get(indexSecondVertex));
            indices.add(list.get(indexSecondVertex + 1));
        }

        return indices;
    }
}


