module es.liernisarraoa.olimpiada {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;


    opens es.liernisarraoa.olimpiada.Modelo to javafx.fxml;
    exports es.liernisarraoa.olimpiada.Modelo;
    opens es.liernisarraoa.olimpiada to javafx.fxml;
    exports es.liernisarraoa.olimpiada;
    exports es.liernisarraoa.olimpiada.Controlador.Olimpiada to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Olimpiada;
    exports es.liernisarraoa.olimpiada.Controlador.Deportista to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Deportista;
    exports es.liernisarraoa.olimpiada.Controlador.Equipo to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Equipo;
    exports es.liernisarraoa.olimpiada.Controlador.Deporte to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Deporte;
    exports es.liernisarraoa.olimpiada.Controlador.Evento to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Evento;
    exports es.liernisarraoa.olimpiada.Controlador;
    opens es.liernisarraoa.olimpiada.Controlador to javafx.fxml;
}