package com.churkovainteam.kghomework.model;

import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;

import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();
}
