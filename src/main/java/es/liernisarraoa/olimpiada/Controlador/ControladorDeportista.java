package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Controlador.Deportista.AniadirControlador;
import es.liernisarraoa.olimpiada.Controlador.Deportista.ModificarControlador;
import es.liernisarraoa.olimpiada.DAO.DaoDeportista;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeportista implements Initializable {
    @FXML
    public TextField nombreFiltrar;
    @FXML
    public TableView<Deportista> tablaDeportistas;
    @FXML
    public TableColumn<Deportista, Integer> columnaID;
    @FXML
    public TableColumn<Deportista, String> columnaNombre;
    @FXML
    public TableColumn<Deportista, String> columnaSexo;
    @FXML
    public TableColumn<Deportista, Integer> columnaCM;
    @FXML
    public TableColumn<Deportista, Integer> columnaKG;
    @FXML
    public TableColumn<Deportista, Image> columnaImagen;

    private Stage stagePrimario;
    private static ObservableList<Deportista> deportistas = FXCollections.observableArrayList();
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

    public void cambiarEquipo(ActionEvent actionEvent) {
    }

    public void cambiarEvento(ActionEvent actionEvent) {
    }

    public void cambiarDeporte(ActionEvent actionEvent) {
    }

    public void cambiarOlimpiada(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/gestionOlimpiada.fxml"));
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
            throw new RuntimeException(e);
        }
    }

    public void filtrarPorNombre(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            String nombreAFiltrar = nombreFiltrar.getText().trim();
            ObservableList<Deportista> deporFiltro = FXCollections.observableArrayList();
            if(!nombreAFiltrar.isEmpty()){
                for(Deportista d : deportistas){
                    if(d.getNombre().equalsIgnoreCase(nombreAFiltrar)){
                        deporFiltro.add(d);
                    }
                }
                //Agregamos el observable list y limpiamos la tabla
                tablaDeportistas.getItems().removeAll();
                tablaDeportistas.setItems(deporFiltro);
            } else {
                tablaDeportistas.getItems().removeAll();
                tablaDeportistas.setItems(deportistas);
            }
        }
    }

    public void aniadirDeportista(ActionEvent actionEvent) {
        //Esto si el controlador necesita hacer algo en la ventana principal
        // Cargar el FXML de la ventana modal
        FXMLLoader loader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/aniadirDepor.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            modalAniadir = new Stage();
            sceneAniadir = new Scene(root);
            // Obtener el controlador de la ventana modal
            AniadirControlador modalControlador = loader.getController();

            // Pasar el TableView al controlador de la ventana modal
            modalControlador.setTablaPersonas(this.tablaDeportistas);
            modalAniadir.setScene(sceneAniadir);
            modalAniadir.initModality(Modality.APPLICATION_MODAL);
            modalAniadir.setTitle("Agregar Persona");
            modalAniadir.setResizable(false);
            modalAniadir.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportistas/icono.png"))));
            modalAniadir.showAndWait();
            deportistas = DaoDeportista.cargarListado();
            tablaDeportistas.getItems().setAll(deportistas);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void modificarDeportista(ActionEvent actionEvent) {
        if(tablaDeportistas.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Seleccion");
            alert.setContentText("No se ha seleccionado ningun registro.");
            alert.showAndWait();
        } else {
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader = new FXMLLoader(OlimpiadaPrincipal.class.getResource("FXML/modificarDepor.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalModificar = new Stage();
                sceneModificar = new Scene(root);
                // Obtener el controlador de la ventana modal
                ModificarControlador modalControlador = loader.getController();

                // Pasar el TableView al controlador de la ventana modal
                modalControlador.setTablaPersonas(this.tablaDeportistas);
                Deportista d = tablaDeportistas.getSelectionModel().getSelectedItem();
                modalControlador.setD(d);
                modalModificar.setScene(sceneModificar);
                modalModificar.initModality(Modality.APPLICATION_MODAL);
                modalModificar.setTitle("Modificar Persona");
                modalModificar.setResizable(false);
                modalModificar.getIcons().add(new Image(String.valueOf(OlimpiadaPrincipal.class.getResource("Imagenes/Deportistas/icono.png"))));
                modalModificar.showAndWait();
                deportistas = DaoDeportista.cargarListado();
                tablaDeportistas.getItems().setAll(deportistas);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("FXML");
                alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                alert.showAndWait();
            }
        }
    }

    public void eliminarDeportista(ActionEvent actionEvent) {
        if(DaoDeportista.eliminarDeportista(tablaDeportistas.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Eliminado");
            alert.setContentText("Se ha eliminado el deportista.");
            alert.showAndWait();
            deportistas = DaoDeportista.cargarListado();
            tablaDeportistas.getItems().setAll(deportistas);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaDeportistas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        columnaKG.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnaCM.setCellValueFactory(new PropertyValueFactory<>("altura"));
        columnaImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        //Para poder poner una imagen en la tabla
        columnaImagen.setCellFactory(col -> new TableCell<Deportista, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item);
                    imageView.setFitWidth(50); // Ajusta el tamaño según sea necesario
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });
        deportistas = DaoDeportista.cargarListado();
        tablaDeportistas.getItems().setAll(deportistas);
    }
}
