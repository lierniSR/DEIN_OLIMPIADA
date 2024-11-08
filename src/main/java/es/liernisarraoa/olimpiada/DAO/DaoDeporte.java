package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Controlador.ControladorEquipo;
import es.liernisarraoa.olimpiada.Modelo.Deporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
