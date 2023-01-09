package com.churkovainteam.kghomework.scene;

import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.TransformedTriangulatedModel;
import com.churkovainteam.kghomework.render_engine.Camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Scene {
    private HashMap<TransformedTriangulatedModel, ModelSceneOptions> models;
    private TransformedTriangulatedModel activeModel;

    private List<Camera> cameras;

    private int activeCameraIndex;

    private final Camera DEFAULT_CAMERA = new Camera(
            new Vector3f(0, 0, 100),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    public Scene() {
        this.models = new HashMap<>();

        this.cameras = new ArrayList<>();
        this.cameras.add(DEFAULT_CAMERA);

        this.activeCameraIndex = 0;
    }

    private void addModel(TransformedTriangulatedModel model, boolean isModelActive) {
        //ModelSceneOptions modelOptions = new ModelSceneOptions();
        //modelOptions.isModelActive = isModelActive;

        models.put(model, new ModelSceneOptions());
    }

    public void addModelToScene(TransformedTriangulatedModel model) {
        addModel(model, false);
    }

    public void deleteModel(String modelName) {
        models.remove(getModel(modelName));
    }

    public void addActiveModelToScene(TransformedTriangulatedModel model) {
        addModel(model, true);
    }

    public void deleteActiveModel(String modelName) {
        addModel(getModel(modelName), false);
    }

    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    public void deleteCamera(int index) {
        cameras.remove(index);

        if (index == activeCameraIndex) {
            activeCameraIndex = 0;
        }
    }

    public void setActiveCamera(int index) {
        activeCameraIndex = index;
    }

    public Camera getActiveCamera() {
        return cameras.get(activeCameraIndex);
    }

    public Set<TransformedTriangulatedModel> getModels() {
        return models.keySet();
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public boolean isModelActive(TransformedTriangulatedModel model) {
        //return models.get(model).isModelActive;
        return model.equals(activeModel);
    }

    public ModelSceneOptions getModelOptions(String modelName) {
        return models.get(getModel(modelName));
    }

    public void addModelSceneOptions(TransformedTriangulatedModel model, ModelSceneOptions options) {
        models.put(model, options);
    }

    public TransformedTriangulatedModel getModel(String modelName) {
        for (var model : models.keySet()) {
            if (model.getModelName().equals(modelName)) {
                return model;
            }
        }

        return null;
    }

    public TransformedTriangulatedModel getActiveModel() {
        return activeModel;
    }

    public void setActiveModel(String modelName) {
        activeModel = getModel(modelName);
    }
}
