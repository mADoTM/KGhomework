package com.churkovainteam.kghomework;

import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.TransformedTriangulatedModel;
import com.churkovainteam.kghomework.objreader.ObjReader;
import com.churkovainteam.kghomework.render_engine.Camera;
import com.churkovainteam.kghomework.render_engine.MovementVector;
import com.churkovainteam.kghomework.render_engine.RenderEngine;
import javafx.fxml.FXML;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class GuiController {

    final private float TRANSLATION = 1.0F;
    public Slider rotateXSlider;
    public Slider rotateYSlider;
    public Slider rotateZSlider;
    public TextField scalerX;
    public TextField scalerY;
    public TextField scalerZ;
    public TextField translateZ;
    public TextField translateY;
    public TextField translateX;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    private TransformedTriangulatedModel mesh;

    private final Camera camera = new Camera(
            new Vector3f(0, 0, 100),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    private Timeline timeline;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas
                    .getGraphicsContext2D()
                    .clearRect(0, 0, width, height);

            camera.setAspectRatio((float) (width / height));

            if (mesh != null) {
                RenderEngine.render(canvas.getGraphicsContext2D(),
                        camera,
                        mesh,
                        (int) width,
                        (int) height);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            String fileContent = Files.readString(fileName);
            mesh = new TransformedTriangulatedModel(ObjReader.read(fileContent));
            // todo: обработка ошибок
        } catch (IOException exception) {

        }
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.FORWARD, TRANSLATION);
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.BACKWARD, TRANSLATION);
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.LEFT, TRANSLATION);
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.RIGHT, TRANSLATION);
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.UP, TRANSLATION);
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        camera.movePosition(MovementVector.DOWN, TRANSLATION);
    }

    @FXML
    public void rotateCameraAroundXLeft(ActionEvent actionEvent) {
        camera.rotateCameraHorizontal(TRANSLATION);
    }

    @FXML
    public void rotateCameraAroundXRight(ActionEvent actionEvent) {
        camera.rotateCameraHorizontal(-TRANSLATION);
    }

    @FXML
    public void rotateCameraAroundXUp(ActionEvent actionEvent) {
        camera.rotateCameraVertical(TRANSLATION);
    }

    @FXML
    public void rotateCameraAroundXDown(ActionEvent actionEvent) {
        camera.rotateCameraVertical(-TRANSLATION);
    }

    public void onRotateSlider(MouseEvent keyEvent) {
        if(mesh == null) {
            return;
        }

        mesh.setRotate(new Vector3f((float) rotateXSlider.getValue(),
                (float) rotateYSlider.getValue(),
                (float) rotateZSlider.getValue()));
    }

    public void onScaleTextChange(KeyEvent inputMethodEvent) {
        if(mesh == null) {
            return;
        }
        mesh.setScale(new Vector3f(Float.parseFloat(scalerX.getText()),
                Float.parseFloat(scalerY.getText()),
                Float.parseFloat(scalerZ.getText())));

        mesh.setTranslatedVector(new Vector3f(Float.parseFloat(translateX.getText()),
                Float.parseFloat(translateY.getText()),
                Float.parseFloat(translateZ.getText())));
    }
}