package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RenderingPreparationUtilitiesTest {

    @Test
    void triangulationPolygonWith5Vertex() {
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
    void triangulationPolygonWith3Vertex() {
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
}