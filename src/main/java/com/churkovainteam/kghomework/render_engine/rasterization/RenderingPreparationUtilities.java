package com.churkovainteam.kghomework.render_engine.rasterization;

import com.churkovainteam.kghomework.model.Polygon;

import java.util.*;

public class RenderingPreparationUtilities {
    public static List<Polygon> triangulation(Polygon polygon) {
        List<Integer> vertexIndices = polygon.getVertexIndices();
        List<Polygon> triangularPolygons = new ArrayList<>();

        if (vertexIndices.size() == 3) {
            triangularPolygons.add(polygon);
        } else {

            for (int index = 1; index < vertexIndices.size() - 1; index++) {
                Polygon triangularPolygon = new Polygon();
                List<Integer> threeVertexIndices = new ArrayList<>(Arrays.asList(
                       vertexIndices.get(0), vertexIndices.get(index), vertexIndices.get(index + 1)
                ));
                triangularPolygon.setVertexIndices(threeVertexIndices);

                triangularPolygons.add(triangularPolygon);
            }
        }

        System.out.println(Arrays.toString(new List[]{triangularPolygons}));
        return triangularPolygons;
    }
}


