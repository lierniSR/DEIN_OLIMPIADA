package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Controlador.Olimpiada.AniadirControladorOlim;
import es.liernisarraoa.olimpiada.Controlador.Olimpiada.ModificarControladorOlim;
import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
import es.liernisarraoa.olimpiada.OlimpiadaPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorTablaOlimpiada implements Initializable {
    @FXML
    public TextField nombreFiltrar;
    @FXML
    public TableView<Olimpiada> tablaOlimpiadas;
    @FXML
    public TableColumn<Olimpiada, Integer> columnaID;
    @FXML
    public TableColumn<Olimpiada, String> columnaNombre;
    @FXML
    public TableColumn<Olimpiada, String> columnaCiudad;
    @FXML
    public TableColumn<Olimpiada, String> columnaTemporada;
    @FXML
    public TableColumn<Olimpiada, Integer> columnaAnio;

    private Stage stagePrimario;
    private static ObservableList<Olimpiada> olimpiadas = FXCollections.observableArrayList();
    private Scene sceneAniadir;
    private Stage modalAniadir;
    private Scene sceneModificar;
    private Stage modalModificar;
    private Olimpiada o;

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
//throw new RuntimeException(e);
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
            ObservableList<Olimpiada> olimFiltro = FXCollections.observableArrayList();
            if(!nombreAFiltrar.isEmpty()){
                for(Olimpiada o : olimpiadas){
                    if(o.getNombre().equalsIgnoreCase(nombreAFiltrar)){
                        olimFiltro.add(o);
                    }
                }
                //Agregamos el observable list y limpiamos la tabla
                tablaOlimpiadas.getItems().removeAll();
                tablaOlimpiadas.setItems(olimFiltro);
            } else {
                tablaOlimpiadas.getItems().removeAll();
                tablaOlimpiadas.setItems(olimpiadas);
            }
        }
    }

    public void aniadirOlimpiada(ActionEvent actionEvent) {
       //Esto si el controlador necesita hacer algo en la ventana principal
        // Cargar el FXML de la ventana modal
        FXMLLoader loader =  new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Olimpiada/aniadirOlim.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            modalAniadir = new Stage();
            sceneAniadir = new Scene(root);
            // Obtener el controlador de la ventana modal
            AniadirControladorOlim modalControlador = loader.getController();

            // Pasar el TableView al controlador de la ventana modal
            modalControlador.setTablaOlimpiada(this.tablaOlimpiadas);
            modalAniadir.setScene(sceneAniadir);
            modalAniadir.initModality(Modality.APPLICATION_MODAL);
            modalAniadir.setTitle("Agregar Olimpiada");
            modalAniadir.setResizable(false);
            modalAniadir.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Olimpiadas/icono.png"))));
            modalAniadir.showAndWait();
            olimpiadas = DaoOlimpiada.cargarListado();
            tablaOlimpiadas.getItems().setAll(olimpiadas);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void modificarOlimpiada(ActionEvent actionEvent) {
        if(tablaOlimpiadas.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Seleccion");
            alert.setContentText("No se ha seleccionado ningun registro.");
            alert.showAndWait();
        } else {
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Olimpiada/modificarOlimpiada.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalModificar = new Stage();
                sceneModificar = new Scene(root);
                // Obtener el controlador de la ventana modal
                ModificarControladorOlim modalControlador = loader.getController();

                // Pasar el TableView al controlador de la ventana modal
                modalControlador.setTablaOlimpiada(this.tablaOlimpiadas);
                Olimpiada o = tablaOlimpiadas.getSelectionModel().getSelectedItem();
                modalControlador.setO(o);
                modalModificar.setScene(sceneModificar);
                modalModificar.initModality(Modality.APPLICATION_MODAL);
                modalModificar.setTitle("Modificar Olimpiada");
                modalModificar.setResizable(false);
                modalModificar.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Olimpiadas/icono.png"))));
                modalModificar.showAndWait();
                olimpiadas = DaoOlimpiada.cargarListado();
                tablaOlimpiadas.getItems().setAll(olimpiadas);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("FXML");
                alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                alert.showAndWait();
            }
        }
    }

    public void eliminarOlimpiada(ActionEvent actionEvent) {
        if(DaoOlimpiada.eliminarOlimpiadas(tablaOlimpiadas.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Eliminado");
            alert.setContentText("Se ha eliminado el deportista.");
            alert.showAndWait();
            olimpiadas = DaoOlimpiada.cargarListado();
            tablaOlimpiadas.getItems().setAll(olimpiadas);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaOlimpiadas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id_olimpiada"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        columnaTemporada.setCellValueFactory(new PropertyValueFactory<>("temporada"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        olimpiadas = DaoOlimpiada.cargarListado();
        tablaOlimpiadas.getItems().setAll(olimpiadas);
    }
}
