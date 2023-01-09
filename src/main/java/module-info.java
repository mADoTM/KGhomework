module com.churkovainteam.kghomework {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.junit.jupiter.api;

    opens com.churkovainteam.kghomework to javafx.fxml;
    exports com.churkovainteam.kghomework;
    exports com.churkovainteam.kghomework.scene;
    opens com.churkovainteam.kghomework.scene to javafx.fxml;
}