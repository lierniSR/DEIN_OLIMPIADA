package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEvento {
    private static ConexionBBDD conexion;

    public static ObservableList<Evento> cargarListado() {
        ObservableList<Evento> eventos = FXCollections.observableArrayList();

        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT evento.id_evento, evento.nombre, olimpiada.nombre, deporte.nombre FROM evento INNER JOIN olimpiada ON olimpiada.id_olimpiada = evento.id_olimpiada INNER JOIN deporte ON deporte.id_deporte = evento.id_deporte";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                Evento evento = new Evento(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4));
                eventos.add(evento);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BBDD");
            alert.setContentText("No se ha podido conectar a la base de datos.");
            alert.showAndWait();
        }
        return eventos;
    }

    public static boolean insertarEquipo(Evento e) {
        int lineas = 0;
        Integer id_olimpiada = obtenerIdOlimpiada(e.getNombreOlimpiada());
        Integer id_deporte = obtenerIdDeporte(e.getNombreDeporte());
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "INSERT INTO evento(nombre, id_olimpiada, id_deporte) VALUES (?,?,?)";
            try {
                PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
                pstmt.setString(1, e.getNombreEvento());
                pstmt.setInt(2, id_olimpiada);
                pstmt.setInt(3, id_deporte);

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

    private static Integer obtenerIdDeporte(String nombreDeporte) {
        int id = -1;
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT id_deporte FROM deporte WHERE nombre = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, nombreDeporte);
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                id = resultado.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private static Integer obtenerIdOlimpiada(String nombreOlimpiada) {
        int id = -1;
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT id_olimpiada FROM olimpiada WHERE nombre = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, nombreOlimpiada);
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                id = resultado.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static ObservableList<String> cargarNombresOlimpiadas() {
        ObservableList<String> nombres = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT nombre FROM olimpiada";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                nombres.add(resultados.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombres;
    }

    public static ObservableList<String> cargarNombresDeportes() {
        ObservableList<String> nombres = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBBDD();
            String sql = "SELECT nombre FROM deporte";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            ResultSet resultados = pstmt.executeQuery();
            while(resultados.next()){
                nombres.add(resultados.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombres;
    }

    public static boolean modificarEquipo(Evento e) {
        Integer id_olimpiada = obtenerIdOlimpiada(e.getNombreOlimpiada());
        Integer id_deporte = obtenerIdDeporte(e.getNombreDeporte());
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            InputStream inputStream = null;
            String sql = "UPDATE evento SET nombre = ?, id_olimpiada = ?, id_deporte = ? WHERE id_evento = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(sql);
            pstmt.setString(1, e.getNombreEvento());
            pstmt.setInt(2, id_olimpiada);
            pstmt.setInt(3, id_deporte);
            pstmt.setInt(4, e.getId_evento());
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

    public static boolean eliminarEvento(Evento evento) {
        int lineas = 0;
        try {
            conexion = new ConexionBBDD();
            String sql = "DELETE FROM evento WHERE id_evento = ?";
            PreparedStatement pstm = conexion.getConexion().prepareStatement(sql);
            pstm.setInt(1, evento.getId_evento());
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
