package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Controlador.Deporte.AniadirDeporControlador;
import es.liernisarraoa.olimpiada.Controlador.Deporte.MoidificarDeporControlador;
import es.liernisarraoa.olimpiada.Controlador.Equipo.AniadirEquipoControlador;
import es.liernisarraoa.olimpiada.Controlador.Equipo.ModificarControlador;
import es.liernisarraoa.olimpiada.DAO.DaoDeporte;
import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.Modelo.Deporte;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeporte implements Initializable {

    private Stage stagePrimario;
    private Scene sceneAniadir;
    private Stage modalAniadir;
    private Scene sceneModificar;
    private Stage modalModificar;

    @FXML
    public TextField nombreFiltrar;
    @FXML
    public TableView<Deporte> tablaDeportes;
    @FXML
    public TableColumn<Deporte, Integer> columnaID;
    @FXML
    public TableColumn<Deporte, String> columnaNombre;

    private static ObservableList<Deporte> deportes = FXCollections.observableArrayList();

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
        if(keyEvent.getCode() == KeyCode.ENTER){
            String nombreAFiltrar = nombreFiltrar.getText().trim();
            ObservableList<Deporte> deporteFiltro = FXCollections.observableArrayList();
            if(!nombreAFiltrar.isEmpty()){
                for(Deporte d : deportes){
                    if(d.getNombre().equalsIgnoreCase(nombreAFiltrar)){
                        deporteFiltro.add(d);
                    }
                }
                //Agregamos el observable list y limpiamos la tabla
                tablaDeportes.getItems().removeAll();
                tablaDeportes.setItems(deporteFiltro);
            } else {
                tablaDeportes.getItems().removeAll();
                tablaDeportes.setItems(deportes);
            }
        }
    }

    public void aniadirDeporte(ActionEvent actionEvent) {
        //Esto si el controlador necesita hacer algo en la ventana principal
        // Cargar el FXML de la ventana modal
        FXMLLoader loader =  new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deporte/aniadirDeporte.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            modalAniadir = new Stage();
            sceneAniadir = new Scene(root);
            // Obtener el controlador de la ventana modal
            AniadirDeporControlador modalControlador = loader.getController();

            // Pasar el TableView al controlador de la ventana modal
            modalControlador.setTablaDeporte(this.tablaDeportes);
            modalAniadir.setScene(sceneAniadir);
            modalAniadir.initModality(Modality.APPLICATION_MODAL);
            modalAniadir.setTitle("Agregar Deporte");
            modalAniadir.setResizable(false);
            modalAniadir.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportes/icono.png"))));
            modalAniadir.showAndWait();
            deportes = DaoDeporte.cargarListado();
            tablaDeportes.getItems().setAll(deportes);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public void modificarDeporte(ActionEvent actionEvent) {
        if(tablaDeportes.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Seleccion");
            alert.setContentText("No se ha seleccionado ningun registro.");
            alert.showAndWait();
        } else {
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader =  new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Deporte/modificarDeporte.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalModificar = new Stage();
                sceneModificar = new Scene(root);
                // Obtener el controlador de la ventana modal
                MoidificarDeporControlador modalControlador = loader.getController();

                // Pasar el TableView al controlador de la ventana modal
                modalControlador.setTablaDeporte(this.tablaDeportes);
                Deporte deporte = tablaDeportes.getSelectionModel().getSelectedItem();
                modalControlador.setD(deporte);
                modalModificar.setScene(sceneModificar);
                modalModificar.initModality(Modality.APPLICATION_MODAL);
                modalModificar.setTitle("Modificar Deporte");
                modalModificar.setResizable(false);
                modalModificar.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportes/icono.png"))));
                modalModificar.showAndWait();
                deportes = DaoDeporte.cargarListado();
                tablaDeportes.getItems().setAll(deportes);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("FXML");
                alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        }
    }

    public void eliminarDeporte(ActionEvent actionEvent) {
        if(DaoDeporte.eliminarDeporte(tablaDeportes.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Eliminado");
            alert.setContentText("Se ha eliminado el deporte.");
            alert.showAndWait();
            deportes = DaoDeporte.cargarListado();
            tablaDeportes.getItems().setAll(deportes);
        }
    }

    public void clicar(MouseEvent mouseEvent) {
        tablaDeportes.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaDeportes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id_deporte"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        deportes = DaoDeporte.cargarListado();
        tablaDeportes.getItems().setAll(deportes);
    }
}
