package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Controlador.ControladorEquipo;
import es.liernisarraoa.olimpiada.Modelo.Deporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDeporte {
    private static ConexionBBDD conexion;

    public static ObservableList<Deporte> cargarListado() {
        ObservableList<Deporte> deportes = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT * FROM deporte";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Deporte deporte = new Deporte(resultados.getInt(1), resultados.getString(2));
                deportes.add(deporte);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deportes;
    }

    public static boolean insertarDeporte(Deporte d) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "INSERT INTO deporte(nombre) VALUES (?)";
            try {
                PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
                pstmt.setString(1, d.getNombre());

                lineas = pstmt.executeUpdate();
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("SQL");
                alert.setContentText("No se ha podido ejecutar la sentencia.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lineas > 0;
    }

    public static boolean modificarEquipo(Deporte d) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "UPDATE deporte SET nombre = ? WHERE id_deporte = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, d.getNombre());
            pstmt.setInt(2, d.getId_deporte());
            lineas = pstmt.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SQL");
            alert.setContentText("No se ha podido ejecutar la sentencia.");
            alert.showAndWait();
            throw new RuntimeException(ex);
        }
        return lineas > 0;
    }
}
