package es.liernisarraoa.olimpiada.Controlador.Deportista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AniadirControlador implements Initializable {
    private String errores = "";
    private Image imagen;
    private FileChooser ficheroEleccion;
    private static Stage stagePrincipal;

    @FXML
    public TextField nombreTextField;
    @FXML
    public ChoiceBox seleccionadorSexo;
    @FXML
    public TextField pesoTextField;
    @FXML
    public TextField alturaTextField;

    public void seleccionFichero(ActionEvent actionEvent) {
        ficheroEleccion.setTitle("Selecciona un fichero");
        ficheroEleccion.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PNG", "*.png"));
        imagen = new Image(String.valueOf(ficheroEleccion.showOpenDialog(stagePrincipal)));
    }

    public void guardarDeportista(ActionEvent actionEvent) {
        comprobarErrores();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(errores);
        alert.showAndWait();
    }

    public void cerrarModal(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> opciones = FXCollections.observableArrayList("Femenino", "Masculino");
        seleccionadorSexo.setItems(opciones);
    }

    public void comprobarErrores(){
        errores = "";
        if(imagen == null){
            errores += "No se ha seleccionado ningun archivo.\n";
        }
        if(nombreTextField.getText().isEmpty()){
            errores += "El campo nombre se encuentra vacío.\n";
        }
        if(seleccionadorSexo.getValue() == null){
            errores += "No se ha seleccionado ningun sexo\n";
        }
        if(!pesoTextField.getText().isEmpty()){
            try{
                Integer num = Integer.parseInt(pesoTextField.getText());
            } catch (NumberFormatException e){
                errores += "El campo peso contiene un caracter.\n";
            }
        }
        if(!alturaTextField.getText().isEmpty()){
            try{
                Integer num = Integer.parseInt(alturaTextField.getText());
            } catch (NumberFormatException e){
                errores += "El campo peso contiene un caracter.\n";
            }
        }
    }

    public static void setStagePrincipal(Stage stage){
        stagePrincipal = stage;
    }
}
