package es.liernisarraoa.olimpiada.DAO;

import es.liernisarraoa.olimpiada.Conexion.ConexionBBDD;
import es.liernisarraoa.olimpiada.Modelo.Equipo;
import es.liernisarraoa.olimpiada.Modelo.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
}
