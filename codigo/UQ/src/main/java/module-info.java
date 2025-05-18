module co.edu.uniquindio.uq {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens co.edu.uniquindio.uq to javafx.fxml;
    exports co.edu.uniquindio.uq;
    opens co.edu.uniquindio.uq.controller to javafx.fxml;
    exports co.edu.uniquindio.uq.controller;
    exports co.edu.uniquindio.uq.model;
    opens co.edu.uniquindio.uq.model to javafx.fxml;
    exports co.edu.uniquindio.uq.viewController;
    opens co.edu.uniquindio.uq.viewController to javafx.fxml;
    exports co.epackage;
    opens co.epackage to javafx.fxml;

}