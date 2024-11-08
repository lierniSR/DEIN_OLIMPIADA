package es.liernisarraoa.olimpiada.Controlador.Deporte;

import es.liernisarraoa.olimpiada.DAO.DaoDeporte;
import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.Modelo.Deporte;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoidificarDeporControlador {
    @FXML
    public TextField nombreText;

    private TableView<Deporte> tablaDeporte;
    private String errores = "";
    private Deporte d;

    public void guardarDeportista(ActionEvent actionEvent) {
        comprobarErrores();
        if(errores.isEmpty()){
            d = new Deporte(d.getId_deporte(), nombreText.getText());
            boolean repetido = false;
            for(Deporte deporte : tablaDeporte.getItems()){
                if (deporte.equals(d)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoDeporte.modificarEquipo(this.d);
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

    public void setTablaDeporte(TableView<Deporte> tablaDeporte){
        this.tablaDeporte = tablaDeporte;
    }

    public void setD(Deporte d){
        this.d = d;
        nombreText.setText(this.d.getNombre());
    }

    private void alertaEquipoAniadir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Modificado");
        alert.setContentText("Deporte modificado.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText.getText().isEmpty()){
            errores += "El campo nombre se encuntra vacio.\n";
        }
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Deporte duplicado");
        alert.showAndWait();
    }
}
