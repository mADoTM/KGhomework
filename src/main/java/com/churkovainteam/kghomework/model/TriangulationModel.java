package com.churkovainteam.kghomework.model;

import com.churkovainteam.kghomework.render_engine.rasterization.RenderingPreparationUtilities;

import java.util.ArrayList;
import java.util.List;

public class TriangulationModel {
    private final Model initialModel;
    private final List<Polygon> triangulationPolygons;

    public TriangulationModel(Model initialModel) {
        this.initialModel = initialModel;
        this.triangulationPolygons = triangulatePolygons(initialModel.polygons);
    }

    public Model getInitialModel() {
        return initialModel;
    }

    public List<Polygon> getTriangulationPolygons() {
        return triangulationPolygons;
    }

    public static List<Polygon> triangulatePolygons(List<Polygon> initialPolygons) {
        List<Polygon> triangulationPolygons = new ArrayList<>();

        for (Polygon polygon : initialPolygons) {
            triangulationPolygons.addAll(RenderingPreparationUtilities.triangulation(polygon));
        }

        return triangulationPolygons;
    }
}
