module com.churkovainteam.kghomework {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires vecmath;

    opens com.churkovainteam.kghomework to javafx.fxml;
    exports com.churkovainteam.kghomework;
}