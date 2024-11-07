package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Olimpiada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDeportista {
    private static ConexionBBDD conexion;

    public static ObservableList<Deportista> cargarListado(){
        ObservableList<Deportista> deportistas = FXCollections.observableArrayList();
        Image imagen;
        String sexoString;
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT * FROM olimpiadas.deportista;";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                //Para intercambiar de Blob a Image
                Blob blob = resultados.getBlob(6);
                if(blob != null){
                    InputStream input = blob.getBinaryStream();
                    imagen = new Image(input);
                } else {
                    imagen = new Image(String.valueOf(Olimpiada.class.getResource("Imagenes/Deportistas/noFoto.png")));
                }
                if(resultados.getString(3).equals("M")){
                    sexoString = "Masculino";
                } else {
                    sexoString = "Femenino";
                }
                Deportista d = new Deportista(resultados.getInt(1), resultados.getString(2), sexoString, resultados.getInt(4), resultados.getInt(5), imagen);
                deportistas.add(d);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BBDD");
            alert.setContentText("No se ha podido conectar a la base de datos.");
            alert.showAndWait();
        }
        return deportistas;
    }
}
