package co.edu.uniquindio.uq.viewController;

import co.edu.uniquindio.uq.controller.SistemaHospitalario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReporteCitasController {

    @FXML
    private TextArea txtReporte;

    private final SistemaHospitalario sistema = SistemaHospitalario.getInstance();

    @FXML
    public void generarReporteCitas() {
        ObservableList<String> citas = sistema.generarReporteCitas();
        if (citas.isEmpty()) {
            txtReporte.setText("No hay citas registradas.");
        } else {
            StringBuilder reporte = new StringBuilder();
            for (String linea : citas) {
                reporte.append(linea).append("\n");
            }
            txtReporte.setText(reporte.toString());
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/uq/Administrador.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

