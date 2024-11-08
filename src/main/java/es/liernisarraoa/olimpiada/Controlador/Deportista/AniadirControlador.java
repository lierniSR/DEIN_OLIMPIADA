package es.liernisarraoa.olimpiada.Controlador.Deportista;

import es.liernisarraoa.olimpiada.DAO.DaoDeportista;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AniadirControlador implements Initializable {
    private String errores = "";
    private Image imagen = null;
    private FileChooser ficheroEleccion = new FileChooser();
    private static Stage stagePrincipal;
    private Deportista d;
    private TableView<Deportista> tablaDeportista;

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
        File file = new File(ficheroEleccion.showOpenDialog(stagePrincipal).getAbsolutePath());
        imagen = new Image(file.toURI().toString());
    }

    public void guardarDeportista(ActionEvent actionEvent) {
        Deportista d = null;
        comprobarErrores();
        if(errores.isEmpty()){
            if(pesoTextField.getText().isEmpty()){
                if(alturaTextField.getText().isEmpty()){
                    Integer id = tablaDeportista.getItems().getLast().getId() + 1;
                    d = new Deportista(id, nombreTextField.getText(), seleccionadorSexo.getValue().toString(), 0,0, imagen);
                    boolean repetido = false;
                    for(Deportista depor : tablaDeportista.getItems()){
                        if(depor.equals(d)){
                            repetido = true;
                            break;
                        }
                    }
                    if(!repetido){
                        boolean agregado = DaoDeportista.aniadirDepor(d);
                        alertaDeporAniadir();
                        ((Stage)nombreTextField.getScene().getWindow()).close();
                    } else {
                        alertaRepetido();
                    }
                } else {
                    Integer id = tablaDeportista.getItems().getLast().getId() + 1;
                    d = new Deportista(id, nombreTextField.getText(), seleccionadorSexo.getValue().toString(), 0, Integer.parseInt(alturaTextField.getText()), imagen);
                    boolean repetido = false;
                    for(Deportista depor : tablaDeportista.getItems()){
                        if(depor.equals(d)){
                            repetido = true;
                            break;
                        }
                    }
                    if(!repetido){
                        boolean agregado = DaoDeportista.aniadirDepor(d);
                        alertaDeporAniadir();
                        ((Stage)nombreTextField.getScene().getWindow()).close();
                    } else {
                        alertaRepetido();
                    }
                }
            } else {
                if(alturaTextField.getText().isEmpty()){
                    Integer id = tablaDeportista.getItems().getLast().getId() + 1;
                    d = new Deportista(id, nombreTextField.getText(), seleccionadorSexo.getValue().toString(), Integer.parseInt(pesoTextField.getText()),0, imagen);
                    boolean repetido = false;
                    for(Deportista depor : tablaDeportista.getItems()){
                        if (depor.equals(d)) {
                            repetido = true;
                            break;
                        }
                    }
                    if(!repetido){
                        boolean agregado = DaoDeportista.aniadirDepor(d);
                        alertaDeporAniadir();
                        ((Stage)nombreTextField.getScene().getWindow()).close();
                    } else {
                        alertaRepetido();
                    }
                } else {
                    Integer id = tablaDeportista.getItems().getLast().getId() + 1;
                    d = new Deportista(id, nombreTextField.getText(), seleccionadorSexo.getValue().toString(), Integer.parseInt(pesoTextField.getText()), Integer.parseInt(alturaTextField.getText()), imagen);
                    boolean repetido = false;
                    for(Deportista depor : tablaDeportista.getItems()){
                        if (depor.equals(d)) {
                            repetido = true;
                            break;
                        }
                    }
                    if(!repetido){
                        boolean agregado = DaoDeportista.aniadirDepor(d);
                        alertaDeporAniadir();
                        ((Stage)nombreTextField.getScene().getWindow()).close();
                    } else {
                        alertaRepetido();
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }

    private void alertaRepetido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Deportista duplicado");
        alert.showAndWait();
    }

    private void alertaDeporAniadir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Deportista añadido.");
        alert.setContentText("Se ha añadido el deportista correctamente.");
        alert.showAndWait();
    }

    public void cerrarModal(ActionEvent actionEvent) {
        ((Stage)nombreTextField.getScene().getWindow()).close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> opciones = FXCollections.observableArrayList("Femenino", "Masculino");
        seleccionadorSexo.setItems(opciones);
    }

    public void comprobarErrores(){
        errores = "";
        if(ficheroEleccion == null){
            errores += "Fichero no seleccionado.\n";
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

    /**
     * Establece la referencia a la tabla de personas de la ventana principal.
     * @param tabla La tabla de personas a la que se añadirán las nuevas personas.
     */
    public void setTablaPersonas(TableView<Deportista> tabla){
        this.tablaDeportista = tabla;
    }
}
