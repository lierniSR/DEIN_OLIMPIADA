package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Controlador.Equipo.AniadirEquipoControlador;
import es.liernisarraoa.olimpiada.Controlador.Equipo.ModificarControlador;
import es.liernisarraoa.olimpiada.Controlador.Olimpiada.AniadirControladorOlim;
import es.liernisarraoa.olimpiada.DAO.DaoEquipo;
import es.liernisarraoa.olimpiada.DAO.DaoOlimpiada;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorEquipo implements Initializable {

    @FXML
    public TableView<Equipo> tablaEquipos;
    @FXML
    public TableColumn<Equipo, Integer> columnaID;
    @FXML
    public TableColumn<Equipo, String> columnaNombre;
    @FXML
    public TableColumn<Equipo, String> columnaIniciales;
    @FXML
    public TextField nombreFiltrar;

    private Stage stagePrimario;
    private static ObservableList<Equipo> equipos = FXCollections.observableArrayList();
    private Scene sceneAniadir;
    private Stage modalAniadir;
    private Scene sceneModificar;
    private Stage modalModificar;
    private Equipo e;

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
            ObservableList<Equipo> equipoFiltro = FXCollections.observableArrayList();
            if(!nombreAFiltrar.isEmpty()){
                for(Equipo e : equipos){
                    if(e.getNombre().equalsIgnoreCase(nombreAFiltrar)){
                        equipoFiltro.add(e);
                    }
                }
                //Agregamos el observable list y limpiamos la tabla
                tablaEquipos.getItems().removeAll();
                tablaEquipos.setItems(equipoFiltro);
            } else {
                tablaEquipos.getItems().removeAll();
                tablaEquipos.setItems(equipos);
            }
        }
    }

    public void aniadirEquipo(ActionEvent actionEvent) {
        //Esto si el controlador necesita hacer algo en la ventana principal
        // Cargar el FXML de la ventana modal
        FXMLLoader loader =  new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Equipo/aniadirEquipo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            modalAniadir = new Stage();
            sceneAniadir = new Scene(root);
            // Obtener el controlador de la ventana modal
            AniadirEquipoControlador modalControlador = loader.getController();

            // Pasar el TableView al controlador de la ventana modal
            modalControlador.setTablaEquipo(this.tablaEquipos);
            modalAniadir.setScene(sceneAniadir);
            modalAniadir.initModality(Modality.APPLICATION_MODAL);
            modalAniadir.setTitle("Agregar Equipo");
            modalAniadir.setResizable(false);
            modalAniadir.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Equipos/icono.png"))));
            modalAniadir.showAndWait();
            equipos = DaoEquipo.cargarListado();
            tablaEquipos.getItems().setAll(equipos);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public void modificarEquipo(ActionEvent actionEvent) {
        if(tablaEquipos.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Seleccion");
            alert.setContentText("No se ha seleccionado ningun registro.");
            alert.showAndWait();
        } else {
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader =  new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/Equipo/modificarEquipo.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalAniadir = new Stage();
                sceneAniadir = new Scene(root);
                // Obtener el controlador de la ventana modal
                ModificarControlador modalControlador = loader.getController();

                // Pasar el TableView al controlador de la ventana modal
                modalControlador.setTablaEquipo(this.tablaEquipos);
                Equipo equipo = tablaEquipos.getSelectionModel().getSelectedItem();
                modalControlador.setE(equipo);
                modalAniadir.setScene(sceneAniadir);
                modalAniadir.initModality(Modality.APPLICATION_MODAL);
                modalAniadir.setTitle("Modificar Equipo");
                modalAniadir.setResizable(false);
                modalAniadir.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Equipos/icono.png"))));
                modalAniadir.showAndWait();
                equipos = DaoEquipo.cargarListado();
                tablaEquipos.getItems().setAll(equipos);
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

    public void eliminarEquipo(ActionEvent actionEvent) {
        if(DaoEquipo.eliminarEquipo(tablaEquipos.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Eliminado");
            alert.setContentText("Se ha eliminado el deportista.");
            alert.showAndWait();
            equipos = DaoEquipo.cargarListado();
            tablaEquipos.getItems().setAll(equipos);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaEquipos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id_equipo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaIniciales.setCellValueFactory(new PropertyValueFactory<>("iniciales"));
        equipos = DaoEquipo.cargarListado();
        tablaEquipos.getItems().setAll(equipos);
    }

    public void clicar(MouseEvent mouseEvent) {
        tablaEquipos.getSelectionModel().clearSelection();
    }
}
