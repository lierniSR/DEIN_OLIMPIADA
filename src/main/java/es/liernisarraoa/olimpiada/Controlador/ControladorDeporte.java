package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Modelo.Deporte;
import es.liernisarraoa.olimpiada.OlimpiadaPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorDeporte {
    private Stage stagePrimario;

    @FXML
    public TextField nombreFiltrar;
    @FXML
    public TableView<Deporte> tablaEquipos;
    @FXML
    public TableColumn<Deporte, Integer> columnaID;
    @FXML
    public TableColumn<Deporte, String> columnaNombre;

    /**
     * Set para el stage principal
     */
    public void setStage(Stage stage){
        this.stagePrimario = stage;
    }

    public void cambiarPrincipal(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/olimpiadasPrincipal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Olimpiadas");
            stagePrimario.getIcons().clear();
            stagePrimario.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/icono.jpg"))));
            stagePrimario.setResizable(false);
            stagePrimario.setScene(scene);
            stagePrimario.show();

            ControladorOlimpiada controlador = fxmlLoader.getController();
            controlador.setStage(stagePrimario);
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

    public void cambiarOlimpiada(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Olimpiada/gestionOlimpiada.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de Olimpiadas");
            stagePrimario.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Olimpiadas/icono.png"))));
            stagePrimario.setResizable(false);
            stagePrimario.setScene(scene);
            stagePrimario.show();

            ControladorTablaOlimpiada controladorTablaOlimpiada = fxmlLoader.getController();
            controladorTablaOlimpiada.setStage(stagePrimario);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void cambiarEquipo(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Equipo/gestionEquipo.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de Equipos");
            stagePrimario.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Equipos/icono.png"))));
            stagePrimario.setResizable(false);
            stagePrimario.setScene(scene);
            stagePrimario.show();

            ControladorEquipo controladorEquipo = fxmlLoader.getController();
            controladorEquipo.setStage(stagePrimario);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void cambiarEvento(ActionEvent actionEvent) {
    }

    public void cambiarDeportistas(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deportista/gestionDeportista.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de Deportistas");
            stagePrimario.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportistas/icono.png"))));
            stagePrimario.setResizable(false);
            stagePrimario.setScene(scene);
            stagePrimario.show();

            ControladorDeportista controladorDeportista = fxmlLoader.getController();
            controladorDeportista.setStage(stagePrimario);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void filtrarPorNombre(KeyEvent keyEvent) {
    }

    public void aniadirEquipo(ActionEvent actionEvent) {
    }

    public void modificarEquipo(ActionEvent actionEvent) {
    }

    public void eliminarEquipo(ActionEvent actionEvent) {
    }

    public void clicar(MouseEvent mouseEvent) {
    }

}
