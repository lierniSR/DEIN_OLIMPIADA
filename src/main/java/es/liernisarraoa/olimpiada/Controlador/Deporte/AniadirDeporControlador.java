package es.liernisarraoa.olimpiada.Controlador.Deporte;

import es.liernisarraoa.olimpiada.DAO.DaoDeporte;
import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.Modelo.Deporte;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AniadirDeporControlador {

    @FXML
    public TextField nombreText;

    private TableView<Deporte> tablaDeporte;
    private String errores = "";

    public void setTablaDeporte(TableView<Deporte> tablaDeporte){
        this.tablaDeporte = tablaDeporte;
    }

    public void guardarDeportista(ActionEvent actionEvent) {
        Deporte d = null;
        comprobarErrores();
        if(errores.isEmpty()){
            boolean repetido = false;
            Integer id = tablaDeporte.getItems().getLast().getId_deporte() + 1;
            d = new Deporte(id, nombreText.getText());
            for(Deporte deporte : tablaDeporte.getItems()){
                if (deporte.equals(d)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoDeporte.insertarDeporte(d);
                alertaDeporteAniadido();
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
        alert.setContentText("Deporte duplicado");
        alert.showAndWait();
    }

    private void alertaDeporteAniadido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Añadido");
        alert.setContentText("Deporte añadido.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText.getText().isEmpty()){
            errores += "El campo nombre está vacio.";
        }
    }
}
