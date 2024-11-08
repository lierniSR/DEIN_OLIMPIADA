package es.liernisarraoa.olimpiada.Controlador.Olimpiada;

import es.liernisarraoa.olimpiada.DAO.DaoDeportista;
import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
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

public class AniadirControladorOlim implements Initializable {
    private String errores;
    @FXML
    public TextField nombreText;
    @FXML
    public TextField anioText;
    @FXML
    public ChoiceBox<String> seleccionadorTemporada;
    @FXML
    public TextField ciudadText;
    private TableView<Olimpiada> tablaOlimpiada;

    public void setTablaOlimpiada(TableView<Olimpiada> tablaOlimpiada){
        this.tablaOlimpiada = tablaOlimpiada;
    }

    public void guardarDeportista(ActionEvent actionEvent) {
        Olimpiada o = null;
        comprobarErrores();
        if(errores.isEmpty()){
            boolean repetido = false;
            Integer id = tablaOlimpiada.getItems().getLast().getId_olimpiada() + 1;
            o = new Olimpiada(id, nombreText.getText(), Integer.parseInt(anioText.getText()), seleccionadorTemporada.getValue().toString(), ciudadText.getText());
            for(Olimpiada olim : tablaOlimpiada.getItems()){
                if (olim.equals(o)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoOlimpiada.insertaOlimpiada(o);
                alertaOlimpiadaAniadido();
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
        alert.setContentText("Deportista duplicado");
        alert.showAndWait();
    }

    private void alertaOlimpiadaAniadido() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Añadido");
        alert.setContentText("Olimpiada añadida.");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> opciones = FXCollections.observableArrayList("Summer", "Winter");
        seleccionadorTemporada.setItems(opciones);
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText.getText().isEmpty()){
            errores += "El campo nombre está vacio.\n";
        }
        if(anioText.getText().isEmpty()){
            errores += "El campo año se encuentra vacío.\n";
        } else {
            try{
                Integer anio = Integer.parseInt(anioText.getText());
            } catch(NumberFormatException e){
                errores += "El campo año contiene un caracter.\n";
            }

        }
        if(seleccionadorTemporada.getValue() == null){
            errores += "No se ha seleccionado ninguna temporada\n";
        }
        if(ciudadText.getText().isEmpty()){
            errores += "El campo ciudad esta vacio.\n";
        }
    }
}
