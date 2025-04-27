module co.edu.uniquindio.uq {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens co.edu.uniquindio.uq to javafx.fxml;
    exports co.edu.uniquindio.uq;
}