package es.liernisarraoa.olimpiada;

import es.liernisarraoa.olimpiada.Controlador.ControladorOlimpiada;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

public class Olimpiada extends Application {
    private Stage stagePrimario;

    @Override
    public void start(Stage stage) throws IOException {
        this.stagePrimario = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Olimpiada.class.getResource("FXML/olimpiadasPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),767,502);
        stage.setTitle("Gestion de olimpiadas");
        stage.getIcons().add(new Image(String.valueOf(Olimpiada.class.getResource("Imagenes/icono.jpg"))));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //Pasar al controlador el Stage
        ControladorOlimpiada controlador = fxmlLoader.getController();
        controlador.setStage(stagePrimario);
    }

    public static void main(String[] args) {
        launch();
    }
}