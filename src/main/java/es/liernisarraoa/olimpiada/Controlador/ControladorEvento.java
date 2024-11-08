package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.DAO.DaoEvento;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Evento;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
import es.liernisarraoa.olimpiada.OlimpiadaPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorEvento implements Initializable {
    @FXML
    public TextField nombreFiltrar;
    @FXML
    public TableView<Evento> tablaEventos;
    @FXML
    public TableColumn<Evento, Integer> columnaID;
    @FXML
    public TableColumn<Evento, String> columnaNombre;
    @FXML
    public TableColumn<Evento, String> columnaNombreOlimpiada;
    @FXML
    public TableColumn<Evento, String> columnaNombreDeporte;

    private ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private Stage stagePrimario;
    private Scene sceneAniadir;
    private Stage modalAniadir;
    private Scene sceneModificar;
    private Stage modalModificar;

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

    public void cambiarDeporte(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deporte/gestionDeporte.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de Deportes");
            stagePrimario.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportes/icono.png"))));
            stagePrimario.setResizable(false);
            stagePrimario.setScene(scene);
            stagePrimario.show();

            ControladorDeporte controladorDeporte = fxmlLoader.getController();
            controladorDeporte.setStage(stagePrimario);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void cambiarDeportistas(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deportista/gestionDeportista.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de deportistas");
            stagePrimario.getIcons().clear();
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
        if(keyEvent.getCode() == KeyCode.ENTER){
            String nombreAFiltrar = nombreFiltrar.getText().trim();
            ObservableList<Evento> eventoFiltro = FXCollections.observableArrayList();
            if(!nombreAFiltrar.isEmpty()){
                for(Evento e : eventos){
                    if(e.getNombreEvento().equalsIgnoreCase(nombreAFiltrar)){
                        eventoFiltro.add(e);
                    }
                }
                //Agregamos el observable list y limpiamos la tabla
                tablaEventos.getItems().removeAll();
                tablaEventos.setItems(eventoFiltro);
            } else {
                tablaEventos.getItems().removeAll();
                tablaEventos.setItems(eventos);
            }
        }
    }

    public void clicar(MouseEvent mouseEvent) {
        tablaEventos.getSelectionModel().clearSelection();
    }

    public void aniadirEvento(ActionEvent actionEvent) {
    }

    public void modificarEvento(ActionEvent actionEvent) {
    }

    public void eliminarEvento(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaEventos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id_evento"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEvento"));
        columnaNombreOlimpiada.setCellValueFactory(new PropertyValueFactory<>("nombreOlimpiada"));
        columnaNombreDeporte.setCellValueFactory(new PropertyValueFactory<>("nombreDeporte"));
        eventos = DaoEvento.cargarListado();
        tablaEventos.getItems().setAll(eventos);
    }
}