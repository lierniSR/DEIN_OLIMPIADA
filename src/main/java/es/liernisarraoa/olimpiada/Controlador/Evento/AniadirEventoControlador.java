package es.liernisarraoa.olimpiada.Controlador.Evento;

import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.DAO.DaoEvento;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AniadirEventoControlador implements Initializable {
    @FXML
    public TextField nombreEventoText;
    @FXML
    public ChoiceBox<String> nombreOlimpiadas;
    @FXML
    public ChoiceBox<String> nombresDeporte;


    private TableView<Evento> tablaEvento;
    private String errores = "";

    public void setTablaEvento(TableView<Evento> tablaEvento){
        this.tablaEvento = tablaEvento;
    }

    public void guardarEvento(ActionEvent actionEvent) {
        Evento e = null;
        comprobarErrores();
        if(errores.isEmpty()){
            boolean repetido = false;
            Integer id = tablaEvento.getItems().getLast().getId_evento() + 1;
            e = new Evento(id, nombreEventoText.getText(), nombreOlimpiadas.getValue(), nombresDeporte.getValue());
            for(Evento evento : tablaEvento.getItems()){
                if (evento.equals(e)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoEvento.insertarEquipo(e);
                alertaEquipoAniadido();
                ((Stage)nombreEventoText.getScene().getWindow()).close();
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
        ((Stage)nombreEventoText.getScene().getWindow()).close();
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Evento duplicado");
        alert.showAndWait();
    }

    private void alertaEquipoAniadido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Añadido");
        alert.setContentText("Evento añadido.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreEventoText.getText().isEmpty()){
            errores += "El campo nombre del evento está vacio.\n";
        }
        if(nombreOlimpiadas.getValue().isEmpty()){
            errores += "El campo nombre de la olimpiada esta vacio..\n";
        }
        if(nombresDeporte.getValue().isEmpty()){
            errores += "El campo nombre del deporte esta vacio..\n";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> olimpiadas = DaoEvento.cargarNombresOlimpiadas();
        ObservableList<String> deportes = DaoEvento.cargarNombresDeportes();
        nombreOlimpiadas.setItems(olimpiadas);
        nombresDeporte.setItems(deportes);
    }
}
