package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Deportista;
import es.liernisarraoa.olimpiada.Modelo.Olimpiada;
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
import java.sql.*;


public class DaoOlimpiada {
    static ConexionBBDD conexion;

    public static ObservableList<Olimpiada> cargarListado(){
        ObservableList<Olimpiada> olimpiadas = FXCollections.observableArrayList();
        Image imagen;
        String sexoString;
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT * FROM olimpiadas.olimpiada;";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Olimpiada o = new Olimpiada(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getString(4), resultados.getString(5));
                olimpiadas.add(o);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BBDD");
            alert.setContentText("No se ha podido conectar a la base de datos.");
            alert.showAndWait();
        }
        return olimpiadas;
    }

    public static boolean insertaOlimpiada(Olimpiada o) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "INSERT INTO olimpiada(nombre, anio, temporada, ciudad) VALUES (?,?,?,?)";
            try {
                PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
                pstmt.setString(1, o.getNombre());
                pstmt.setInt(2, o.getAnio());
                pstmt.setString(3, o.getTemporada());
                pstmt.setString(4, o.getCiudad());

                lineas = pstmt.executeUpdate();
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
        return lineas > 0;
    }

    public static boolean modificarOlimpiada(Olimpiada o){
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "UPDATE olimpiada SET nombre = ?, anio = ?, temporada = ?, ciudad = ? WHERE id_olimpiada = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, o.getNombre());
            pstmt.setInt(2, o.getAnio());
            pstmt.setString(3, o.getTemporada());
            pstmt.setString(4, o.getCiudad());
            pstmt.setInt(5, o.getId_olimpiada());
            lineas = pstmt.executeUpdate();
            conexion.cerrarConexion();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("SQL");
                alert.setContentText("No se ha podido ejecutar la sentencia.");
                alert.showAndWait();
            }
        return lineas > 0;
        }

    public static boolean eliminarOlimpiadas(Olimpiada o){
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            String sql = "DELETE FROM olimpiada WHERE id_olimpiada = ?";
            PreparedStatement pstm = conexion.getConexion().prepareStatement(sql);
            pstm.setInt(1, o.getId_olimpiada());
            lineas = pstm.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BBDD");
            alert.setContentText("No se ha podido conectar a la BBDD.");
            alert.showAndWait();
        }
        return lineas > 0;
    }
}
