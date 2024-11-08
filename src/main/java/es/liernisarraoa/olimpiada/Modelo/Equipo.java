package es.liernisarraoa.olimpiada.Modelo;

import java.util.Objects;

public class Equipo {

    private Integer id_equipo;
    private String nombre;
    private String iniciales;


    public Equipo(Integer id_equipo, String nombre, String iniciales){
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.iniciales = iniciales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre) && Objects.equals(iniciales, equipo.iniciales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, iniciales);
    }

    public Integer getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(Integer id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String inciales) {
        this.iniciales = inciales;
    }
}
