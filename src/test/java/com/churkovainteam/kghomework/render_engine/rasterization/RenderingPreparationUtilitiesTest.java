package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.Model;
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

    @Test
    void calculateNormalForVertexInPolygon(){

        Model model = new Model();

        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(38, 2, 3.5f));
        model.vertices.add(new Vector3f(24, 10.3f, 5.6f));
        model.vertices.add(new Vector3f(6.3f, 2.1f, 15.2f));
        ArrayList<Integer> vertexIndices = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(vertexIndices);

        model.polygons.add(polygon);

        polygon = model.polygons.get(0);

        Vector3f result = RenderingPreparationUtilities.calculateNormalForPolygon(polygon, model);
        Vector3f expectedResult = new Vector3f(23.05f, -555.55f, 67.2f);

        Assertions.assertEquals(expectedResult, result);
    }


    @Test
    void calculateNormalForVertexInModel() {

        Model model = new Model();

        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(1, 0, 1));

        ArrayList<Integer> vertexIndices1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ArrayList<Integer> vertexIndices2 = new ArrayList<>(Arrays.asList(0, 1, 4, 5));
        ArrayList<Integer> vertexIndices3 = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        ArrayList<Integer> vertexIndices4 = new ArrayList<>(Arrays.asList(1, 2, 5));
        ArrayList<Integer> vertexIndices5 = new ArrayList<>(Arrays.asList(0, 3, 4));

        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(vertexIndices1);
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(vertexIndices2);
        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(vertexIndices3);
        Polygon polygon4 = new Polygon();
        polygon4.setVertexIndices(vertexIndices4);
        Polygon polygon5 = new Polygon();
        polygon5.setVertexIndices(vertexIndices5);

        model.polygons.add(polygon1);
        model.polygons.add(polygon2);
        model.polygons.add(polygon3);
        model.polygons.add(polygon4);
        model.polygons.add(polygon5);

        RenderingPreparationUtilities.recalculateNormals(model);

        Vector3f expectedResult1 = new Vector3f(1 / 3f, -1 / 3f, 1 / 3f);
        Vector3f expectedResult2 = new Vector3f(1 / 3f, -1 / 3f, 1 / 3f);
        Vector3f expectedResult3 = new Vector3f(1 / 3f, 1 / 3f, 2 / 3f);
        Vector3f expectedResult4 = new Vector3f(1 / 3f, 1 / 3f, 2 / 3f);
        Vector3f expectedResult5 = new Vector3f(1 / 3f, 0, 1 / 3f);
        Vector3f expectedResult6 = new Vector3f(1 / 3f, 0, 1 / 3f);

        ArrayList<Vector3f> expectedResult = new ArrayList<>(Arrays.asList(expectedResult1, expectedResult2, expectedResult3, expectedResult4, expectedResult5, expectedResult6));
        for (int i = 0; i < model.normals.size(); i++) {
            Assertions.assertEquals(expectedResult.get(i), model.normals.get(i));
        }
    }
}