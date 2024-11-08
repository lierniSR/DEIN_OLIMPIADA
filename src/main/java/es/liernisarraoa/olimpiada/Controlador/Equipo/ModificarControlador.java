package es.liernisarraoa.olimpiada.Controlador.Equipo;

import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificarControlador {
    @FXML
    public TextField nombreText;
    @FXML
    public TextField inicialesText;

    private TableView<Equipo> tablaEquipo;
    private String errores = "";
    private Equipo e;

    public void setTablaEquipo(TableView<Equipo> tablaEquipo){
        this.tablaEquipo = tablaEquipo;
    }

    public void setE(Equipo e){
        this.e = e;
        nombreText.setText(this.e.getNombre());
        inicialesText.setText(String.valueOf(this.e.getIniciales()));
    }

    public void guardarDeportista(ActionEvent actionEvent) {
        comprobarErrores();
        if(errores.isEmpty()){
            e = new Equipo(e.getId_equipo(), nombreText.getText(), inicialesText.getText());
            boolean repetido = false;
            for(Equipo equipo : tablaEquipo.getItems()){
                if (equipo.equals(e)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoEquipo.modificarEquipo(this.e);
                alertaEquipoAniadir();
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

    private void alertaEquipoAniadir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Modificado");
        alert.setContentText("Equipo modificado.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText.getText().isEmpty()){
            errores += "El campo nombre se encuntra vacio.\n";
        }
        if(inicialesText.getText().isEmpty()){
            errores += "El campo iniciares se encuentra vacío.\n";
        }
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Equipo duplicado");
        alert.showAndWait();
    }
}
