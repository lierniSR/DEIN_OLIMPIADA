package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEquipo {

    private static ConexionBBDD conexion;

    public static ObservableList<Equipo> cargarListado(){
        ObservableList<Equipo> equipos = FXCollections.observableArrayList();

        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT * FROM equipo";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Equipo equipo = new Equipo(resultados.getInt(1), resultados.getString(2), resultados.getString(3));
                equipos.add(equipo);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BBDD");
            alert.setContentText("No se ha podido conectar a la base de datos.");
            alert.showAndWait();
        }
        return equipos;
    }

    public static boolean insertarEquipo(Equipo e) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "INSERT INTO equipo(nombre, iniciales) VALUES (?,?)";
            try {
                PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
                pstmt.setString(1, e.getNombre());
                pstmt.setString(2, e.getIniciales());

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

    public static boolean modificarEquipo(Equipo e) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "UPDATE equipo SET nombre = ?, iniciales = ? WHERE id_equipo = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, e.getNombre());
            pstmt.setString(2, e.getIniciales());
            pstmt.setInt(3, e.getId_equipo());
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

    public static boolean eliminarEquipo(Equipo equipo) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            String sql = "DELETE FROM equipo WHERE id_equipo = ?";
            PreparedStatement pstm = conexion.getConexion().prepareStatement(sql);
            pstm.setInt(1, equipo.getId_equipo());
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
