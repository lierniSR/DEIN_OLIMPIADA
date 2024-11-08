package es.liernisarraoa.olimpiada.Controlador.Equipo;

import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AniadirEquipoControlador {

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
        Equipo e = null;
        comprobarErrores();
        if(errores.isEmpty()){
            boolean repetido = false;
            Integer id = tablaEquipo.getItems().getLast().getId_equipo() + 1;
            e = new Equipo(id, nombreText.getText(), inicialesText.getText());
            for(Equipo equipo : tablaEquipo.getItems()){
                if (equipo.equals(e)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoEquipo.insertarEquipo(e);
                alertaEquipoAniadido();
                ((Stage)nombreText.getScene().getWindow()).close();
            } else {
                alertaRepetido();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }

    public void cerrarModal(ActionEvent actionEvent) {
        ((Stage)nombreText.getScene().getWindow()).close();
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Equipo duplicado");
        alert.showAndWait();
    }

    private void alertaEquipoAniadido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Añadido");
        alert.setContentText("Equipo añadido.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText.getText().isEmpty()){
            errores += "El campo nombre está vacio.\n";
        }
        if(inicialesText.getText().isEmpty()){
            errores += "El campo iniciales esta vacio..\n";
        }
    }
}
