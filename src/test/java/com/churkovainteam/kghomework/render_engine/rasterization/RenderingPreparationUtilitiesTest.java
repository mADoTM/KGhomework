package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RenderingPreparationUtilitiesTest {

    @Test
    void triangulationPolygonWith5VertexWithoutTextureAndNormal() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));

        List<Polygon> triangularPolygons = RenderingPreparationUtilities.triangulation(initialPolygon);

        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1)));

        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(8, 1, 5)));

        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(8, 5, 3)));

        List<Polygon> expectedPolygons = new ArrayList<>(Arrays.asList(polygon1, polygon2, polygon3));


        StringBuilder result = new StringBuilder();
        StringBuilder expectedResult = new StringBuilder();

        for (Polygon polygon : expectedPolygons) {
            expectedResult.append(polygon);
        }

        for (Polygon polygon : triangularPolygons) {
            result.append(polygon);
        }

        Assertions.assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void triangulationPolygonWith3VertexWithoutTextureAndNormal() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(9, 11, 3)));

        List<Polygon> triangularPolygons = RenderingPreparationUtilities.triangulation(initialPolygon);

        List<Polygon> expectedPolygons = new ArrayList<>();
        expectedPolygons.add(initialPolygon);


        StringBuilder result = new StringBuilder();
        StringBuilder expectedResult = new StringBuilder();

        for (Polygon polygon : expectedPolygons) {
            expectedResult.append(polygon);
        }

        for (Polygon polygon : triangularPolygons) {
            result.append(polygon);
        }

        Assertions.assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void triangulationPolygonWith5VertexWithTextureAndNormal() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));
        initialPolygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 9, 3, 85, 27)));
        initialPolygon.setNormalIndices(new ArrayList<>(Arrays.asList(56, 55, 79, 10, 2)));

        List<Polygon> triangularPolygons = RenderingPreparationUtilities.triangulation(initialPolygon);

        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1)));
        polygon1.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 9, 3)));
        polygon1.setNormalIndices(new ArrayList<>(Arrays.asList(56, 55, 79)));

        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(8, 1, 5)));
        polygon2.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 3, 85)));
        polygon2.setNormalIndices(new ArrayList<>(Arrays.asList(56, 79, 10)));

        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(8, 5, 3)));
        polygon3.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 85, 27)));
        polygon3.setNormalIndices(new ArrayList<>(Arrays.asList(56, 10, 2)));

        List<Polygon> expectedPolygons = new ArrayList<>(Arrays.asList(polygon1, polygon2, polygon3));


        StringBuilder result = new StringBuilder();
        StringBuilder expectedResult = new StringBuilder();

        for (Polygon polygon : expectedPolygons) {
            expectedResult.append(polygon);
        }

        for (Polygon polygon : triangularPolygons) {
            result.append(polygon);
        }

        Assertions.assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void triangulationPolygonWith5VertexWithTextureWithoutNormal() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));
        initialPolygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 9, 3, 85, 27)));

        List<Polygon> triangularPolygons = RenderingPreparationUtilities.triangulation(initialPolygon);

        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1)));
        polygon1.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 9, 3)));

        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(8, 1, 5)));
        polygon2.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 3, 85)));

        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(8, 5, 3)));
        polygon3.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 85, 27)));

        List<Polygon> expectedPolygons = new ArrayList<>(Arrays.asList(polygon1, polygon2, polygon3));


        StringBuilder result = new StringBuilder();
        StringBuilder expectedResult = new StringBuilder();

        for (Polygon polygon : expectedPolygons) {
            expectedResult.append(polygon);
        }

        for (Polygon polygon : triangularPolygons) {
            result.append(polygon);
        }

        Assertions.assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void triangulationPolygonWith5VertexWithNormalWithoutTexture() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));
        initialPolygon.setNormalIndices(new ArrayList<>(Arrays.asList(56, 55, 79, 10, 2)));

        List<Polygon> triangularPolygons = RenderingPreparationUtilities.triangulation(initialPolygon);

        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1)));
        polygon1.setNormalIndices(new ArrayList<>(Arrays.asList(56, 55, 79)));

        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(8, 1, 5)));
        polygon2.setNormalIndices(new ArrayList<>(Arrays.asList(56, 79, 10)));

        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(8, 5, 3)));
        polygon3.setNormalIndices(new ArrayList<>(Arrays.asList(56, 10, 2)));

        List<Polygon> expectedPolygons = new ArrayList<>(Arrays.asList(polygon1, polygon2, polygon3));


        StringBuilder result = new StringBuilder();
        StringBuilder expectedResult = new StringBuilder();

        for (Polygon polygon : expectedPolygons) {
            expectedResult.append(polygon);
        }

        for (Polygon polygon : triangularPolygons) {
            result.append(polygon);
        }

        Assertions.assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void triangulationPolygonWith5VertexWithIncorrectNormalListSize() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));
        initialPolygon.setNormalIndices(new ArrayList<>(Arrays.asList(56, 55, 79, 10)));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> RenderingPreparationUtilities.triangulation(initialPolygon));
    }

    @Test
    void triangulationPolygonWith5VertexWithIncorrectTextureListSize() {
        Polygon initialPolygon = new Polygon();
        initialPolygon.setVertexIndices(new ArrayList<>(Arrays.asList(8, 6, 1, 5, 3)));
        initialPolygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(7, 3, 85, 27)));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> RenderingPreparationUtilities.triangulation(initialPolygon));
    }
}