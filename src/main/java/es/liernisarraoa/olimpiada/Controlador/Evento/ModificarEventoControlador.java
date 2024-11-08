package es.liernisarraoa.olimpiada.Controlador.Evento;

import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.DAO.DaoEvento;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Evento;
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

public class ModificarEventoControlador implements Initializable {
    @FXML
    public TextField nombreEventoText;
    @FXML
    public ChoiceBox<String> nombreOlimpiadas;
    @FXML
    public ChoiceBox<String> nombresDeporte;

    private TableView<Evento> tablaEvento;
    private String errores = "";
    private Evento e;

    public void setTablaEvento(TableView<Evento> tablaEvento){
        this.tablaEvento = tablaEvento;
    }

    public void setE(Evento e){
        this.e = e;
        nombreEventoText.setText(this.e.getNombreEvento());
        nombreOlimpiadas.setValue(this.e.getNombreOlimpiada());
        nombresDeporte.setValue(this.e.getNombreDeporte());
    }

    public void guardarEvento(ActionEvent actionEvent) {
        comprobarErrores();
        if(errores.isEmpty()){
            e = new Evento(e.getId_evento(), nombreEventoText.getText(), nombreOlimpiadas.getValue(), nombresDeporte.getValue());
            boolean repetido = false;
            for(Evento evento : tablaEvento.getItems()){
                if (evento.equals(e)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoEvento.modificarEquipo(this.e);
                alertaEquipoAniadir();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> olimpiadas = DaoEvento.cargarNombresOlimpiadas();
        ObservableList<String> deportes = DaoEvento.cargarNombresDeportes();
        nombreOlimpiadas.setItems(olimpiadas);
        nombresDeporte.setItems(deportes);
    }

    private void alertaEquipoAniadir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Modificado");
        alert.setContentText("Evento modificado.");
        alert.showAndWait();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreEventoText.getText().isEmpty()){
            errores += "El campo nombre del evento se encuntra vacio.\n";
        }
        if(nombresDeporte.getValue().isEmpty()){
            errores += "El campo nombres de deportes se encuentra vacío.\n";
        }
        if(nombreOlimpiadas.getValue().isEmpty()){
            errores += "El campo nombres de olimpiadas se encuentra vacío.\n";
        }
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Evento duplicado");
        alert.showAndWait();
    }
}
