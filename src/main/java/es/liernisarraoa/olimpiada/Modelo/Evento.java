package es.liernisarraoa.olimpiada.Modelo;

import java.util.Objects;

public class Evento {
    private Integer id_evento;
    private String nombreEvento;
    private String nombreOlimpiada;
    private String nombreDeporte;

    public Evento(Integer id_evento, String nombreEvento, String nombreOlimpiada, String nombreDeporte){
        this.id_evento = id_evento;
        this.nombreEvento = nombreEvento;
        this.nombreOlimpiada = nombreOlimpiada;
        this.nombreDeporte = nombreDeporte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nombreEvento, evento.nombreEvento) && Objects.equals(nombreOlimpiada, evento.nombreOlimpiada) && Objects.equals(nombreDeporte, evento.nombreDeporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreEvento, nombreOlimpiada, nombreDeporte);
    }

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreOlimpiada() {
        return nombreOlimpiada;
    }

    public void setNombreOlimpiada(String nombreOlimpiada) {
        this.nombreOlimpiada = nombreOlimpiada;
    }

    public String getNombreDeporte() {
        return nombreDeporte;
    }

    public void setNombreDeporte(String nombreDeporte) {
        this.nombreDeporte = nombreDeporte;
    }
}
