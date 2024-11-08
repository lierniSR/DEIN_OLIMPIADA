module es.liernisarraoa.olimpiada {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;


    opens es.liernisarraoa.olimpiada.Modelo to javafx.fxml;
    exports es.liernisarraoa.olimpiada.Modelo;
    opens es.liernisarraoa.olimpiada to javafx.fxml;
    exports es.liernisarraoa.olimpiada;
    exports es.liernisarraoa.olimpiada.Controlador.Deportista to javafx.fxml;
    opens es.liernisarraoa.olimpiada.Controlador.Deportista;
    exports es.liernisarraoa.olimpiada.Controlador;
    opens es.liernisarraoa.olimpiada.Controlador to javafx.fxml;
}