package es.liernisarraoa.olimpiada.Modelo;

import java.util.Objects;

public class Deporte {

    private Integer id_deporte;
    private String nombre;

    public Deporte(Integer id_deporte, String nombre){
        this.id_deporte = id_deporte;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deporte deporte = (Deporte) o;
        return Objects.equals(nombre, deporte.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public Integer getId_deporte() {
        return id_deporte;
    }

    public void setId_deporte(Integer id_deporte) {
        this.id_deporte = id_deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
