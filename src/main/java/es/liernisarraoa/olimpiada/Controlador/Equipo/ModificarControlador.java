package es.liernisarraoa.olimpiada.Controlador.Equipo;

import es.liernisarraoa.olimpiada.Modelo.Equipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModificarControlador {
    @FXML
    public TextField nombreText;
    @FXML
    public TextField inicialesText;

    private TableView<Equipo> tablaEquipo;
    private String errores = "";

    public void setTablaEquipo(TableView<Equipo> tablaEquipo){
        this.tablaEquipo = tablaEquipo;
    }

    public void guardarDeportista(ActionEvent actionEvent) {
    }

    public void cerrarModal(ActionEvent actionEvent) {
    }
}
