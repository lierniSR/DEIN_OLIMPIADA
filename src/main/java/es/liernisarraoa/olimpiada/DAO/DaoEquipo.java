package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
}