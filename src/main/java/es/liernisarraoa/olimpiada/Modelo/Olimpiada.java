package es.liernisarraoa.olimpiada.Modelo;

import java.util.Objects;

public class Olimpiada {

    private Integer id_olimpiada;
    private String nombre;
    private Integer anio;
    private String temporada;
    private String ciudad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Olimpiada olimpiada = (Olimpiada) o;
        return Objects.equals(nombre, olimpiada.nombre) && Objects.equals(anio, olimpiada.anio) && Objects.equals(temporada, olimpiada.temporada) && Objects.equals(ciudad, olimpiada.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, anio, temporada, ciudad);
    }

    public Integer getId_olimpiada() {
        return id_olimpiada;
    }

    public void setId_olimpiada(Integer id_olimpiada) {
        this.id_olimpiada = id_olimpiada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
