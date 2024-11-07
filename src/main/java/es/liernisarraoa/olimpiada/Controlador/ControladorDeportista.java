package es.liernisarraoa.olimpiada.Controlador;

import es.liernisarraoa.olimpiada.Controlador.Deportista.AniadirControlador;
import es.liernisarraoa.olimpiada.DAO.DaoDeportista;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Olimpiada;
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
    public TableView tablaDeportistas;
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

    /**
     * Set para el stage principal
     */
    public void setStage(Stage stage){
        this.stagePrimario = stage;
    }

    public void cambiarPrincipal(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Olimpiada.class.getResource("FXML/olimpiadasPrincipal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 767, 502);
            stagePrimario.setTitle("Gestion de olimpiadas");
            stagePrimario.getIcons().clear();
            stagePrimario.getIcons().add(new Image(String.valueOf(Olimpiada.class.getResource("Imagenes/icono.jpg"))));
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
        FXMLLoader loader = new FXMLLoader(Olimpiada.class.getResource("FXML/aniadirDepor.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            // Obtener el controlador de la ventana modal
            //AniadirControlador modalControlador = loader.getController();

            // Pasar el TableView al controlador de la ventana modal
            //modalControlador.setTablaPersonas(this.tablaPersonas);

            // Crear y mostrar la ventana modal
            modalAniadir = new Stage();
            sceneAniadir = new Scene(root);
            modalAniadir.setScene(sceneAniadir);
            modalAniadir.initModality(Modality.APPLICATION_MODAL);
            modalAniadir.setTitle("Agregar Persona");
            modalAniadir.setResizable(false);
            modalAniadir.getIcons().add(new Image(String.valueOf(Olimpiada.class.getResource("Imagenes/Deportistas/icono.png"))));
            modalAniadir.showAndWait();
            deportistas = DaoDeportista.cargarListado();
            tablaDeportistas.getItems().setAll(deportistas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarDeportista(ActionEvent actionEvent) {
    }

    public void eliminarDeportista(ActionEvent actionEvent) {
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
