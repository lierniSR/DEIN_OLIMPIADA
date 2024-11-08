package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.OlimpiadaPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControladorOlimpiada implements Initializable {

    private String diaString;
    private Stage stage;

    @FXML
    public Label dia;

    public void cambiarDeportista(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deportista/gestionDeportista.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stage.setTitle("Gestion de Deportistas");
            stage.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportistas/icono.png"))));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            ControladorDeportista controladorDeportista = fxmlLoader.getController();
            controladorDeportista.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void cambiarParticipacion(ActionEvent actionEvent) {
    }

    public void cambiarEquipo(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Equipo/gestionEquipo.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stage.setTitle("Gestion de Equipos");
            stage.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Equipos/icono.png"))));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            ControladorEquipo controladorEquipo = fxmlLoader.getController();
            controladorEquipo.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public void cambiarEvento(ActionEvent actionEvent) {
    }

    public void cambiarDeporte(ActionEvent actionEvent) {
    }

    public void cambiarOlimpiada(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Olimpiada/gestionOlimpiada.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stage.setTitle("Gestion de Olimpiadas");
            stage.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Olimpiadas/icono.png"))));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            ControladorTablaOlimpiada controladorTablaOlimpiada = fxmlLoader.getController();
            controladorTablaOlimpiada.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    /**
     * Set para el stage principal
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertarFecha();
    }

    private void insertarFecha() {
        //Para poner la fecha en la pantalla principal
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] partesFecha = hoy.format(formatoFecha).split("/");
        if(partesFecha[1].equals("01")){
            diaString = partesFecha[0] + ", Enero del " + partesFecha[2];
        }
        if(partesFecha[1].equals("02")){
            diaString = partesFecha[0] + ", Febrero del " + partesFecha[2];
        }
        if(partesFecha[1].equals("03")){
            diaString = partesFecha[0] + ", Marzo del " + partesFecha[2];
        }
        if(partesFecha[1].equals("04")){
            diaString = partesFecha[0] + ", Abril del " + partesFecha[2];
        }
        if(partesFecha[1].equals("05")){
            diaString = partesFecha[0] + ", Mayo del " + partesFecha[2];
        }
        if(partesFecha[1].equals("06")){
            diaString = partesFecha[0] + ", Junio del " + partesFecha[2];
        }
        if(partesFecha[1].equals("07")){
            diaString = partesFecha[0] + ", Julio del " + partesFecha[2];
        }
        if(partesFecha[1].equals("08")){
            diaString = partesFecha[0] + ", Agosto del " + partesFecha[2];
        }
        if(partesFecha[1].equals("09")){
            diaString = partesFecha[0] + ", Septiembre del " + partesFecha[2];
        }
        if(partesFecha[1].equals("10")){
            diaString = partesFecha[0] + ", Octubre del " + partesFecha[2];
        }
        if(partesFecha[1].equals("11")){
            diaString = partesFecha[0] + ", Noviembre del " + partesFecha[2];
        }
        if(partesFecha[1].equals("12")){
            diaString = partesFecha[0] + ", Diciembre del " + partesFecha[2];
        }
        dia.setText(diaString);
    }
}