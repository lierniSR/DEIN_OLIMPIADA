package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Olimpiada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public static void aniadirDepor(Deportista d){
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "INSERT INTO deportista(nombre, sexo, peso, altura, foto) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
                pstmt.setString(1, d.getNombre());
                if(d.getSexo().equals("Masculino")){
                    pstmt.setString(2, "M");
                } else {
                    pstmt.setString(2, "F");
                }
                pstmt.setInt(3,d.getPeso());
                pstmt.setInt(4, d.getAltura());
                if(d.getImagen() != null){
                    //Convertir Image a BufferedImage
                    BufferedImage buffered = SwingFXUtils.fromFXImage(d.getImagen(), null);

                    //Escribir Buffered a byteArray
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        ImageIO.write(buffered, "png", baos);
                        baos.flush();

                        //Convertir ByteArrayOutpuStream a InputStream
                        inputStream = new ByteArrayInputStream(baos.toByteArray());
                        pstmt.setBlob(5, inputStream);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Imagen");
                        alert.setContentText("No se ha podido convertir la imagen.");
                        alert.showAndWait();
                    }
                    int lineas = pstmt.executeUpdate();
                    System.out.println(lineas);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Deportista añadido.");
                    alert.setContentText("Se ha añadido el deportista correctamente.");
                    alert.showAndWait();
                }
                conexion.cerrarConexion();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("SQL");
                alert.setContentText("No se ha podido ejecutar la sentencia.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
