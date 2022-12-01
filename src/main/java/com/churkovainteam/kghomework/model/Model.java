package com.churkovainteam.kghomework.model;

import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;

import java.util.*;

public class Model {
    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector2f> textureVertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Polygon> polygons = new ArrayList<>();
}
