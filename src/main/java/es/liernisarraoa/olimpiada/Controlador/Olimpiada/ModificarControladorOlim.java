package es.liernisarraoa.olimpiada.Controlador.Olimpiada;

import es.liernisarraoa.olimpiada.DAO.DaoDeportista;
import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificarControladorOlim implements Initializable {
    public TextField nombreText;
    public TextField anioText;
    public ChoiceBox seleccionadorTemporada;
    public TextField ciudadText;
    private TableView<Olimpiada> tablaOlimpiada;
    private Olimpiada o;
    private String errores = "";


    public void guardarDeportista(ActionEvent actionEvent) {
        comprobarErrores();
        if(errores.isEmpty()){
            o = new Olimpiada(o.getId_olimpiada(), nombreText.getText(), Integer.parseInt(anioText.getText()), seleccionadorTemporada.getValue().toString(), ciudadText.getText());
            boolean repetido = false;
            for(Olimpiada olim : tablaOlimpiada.getItems()){
                if (olim.equals(o)) {
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                boolean agregado = DaoOlimpiada.modificarOlimpiada(o);
                alertaOlimAniadir();
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

    private void alertaOlimAniadir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Modificado");
        alert.setContentText("Olimpiada modificado.");
        alert.showAndWait();
    }

    public void cerrarModal(ActionEvent actionEvent) {
        ((Stage)nombreText.getScene().getWindow()).close();
    }

    public void comprobarErrores(){
        errores = "";
        if(nombreText == null){
            errores += "Fichero no seleccionado.\n";
        }
        if(anioText.getText().isEmpty()){
            errores += "El campo nombre se encuentra vacío.\n";
        } else {
            try{
                int anio = Integer.parseInt(anioText.getText());
            } catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText(errores);
                alert.showAndWait();
            }
        }
        if(seleccionadorTemporada.getValue() == null){
            errores += "No se ha seleccionado ningun sexo\n";
        }
        if(ciudadText.getText().isEmpty()){
            errores += "El cmapos ciudad está vacio.";
        }
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Olimpiada duplicada");
        alert.showAndWait();
    }

    public void setTablaOlimpiada(TableView<Olimpiada> tabla){
        this.tablaOlimpiada = tabla;
    }
    public void setO(Olimpiada o){
        this.o = o;
        nombreText.setText(this.o.getNombre());
        anioText.setText(String.valueOf(this.o.getAnio()));
        seleccionadorTemporada.setValue(this.o.getTemporada());
        ciudadText.setText(String.valueOf(this.o.getCiudad()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> opciones = FXCollections.observableArrayList("Summer", "Winter");
        seleccionadorTemporada.setItems(opciones);
    }
}
