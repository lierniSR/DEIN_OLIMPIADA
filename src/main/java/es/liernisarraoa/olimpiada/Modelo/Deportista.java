package es.liernisarraoa.olimpiada.Modelo;

import javafx.scene.image.Image;

import java.util.Objects;

public class Deportista {
    private Integer id;
    private String nombre;
    private String sexo;
    private Integer peso;
    private Integer altura;
    private Image imagen;

    /**
     * Constructor de Deportista
     *
     * @param id
     * @param nombre
     * @param sexo
     * @param peso
     * @param altura
     * @param
     */
    public Deportista(Integer id, String nombre, String sexo, Integer peso, Integer altura, Image imagen){
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.imagen = imagen;
    }

    /**
     * Para recibir el ID
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Para poner el ID
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Para recibir el nombre
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Para poner el nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Para recibir el sexo
     * @return String
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Para poner el sexo
     * @param sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Para recibir el peso
     * @return Integer
     */
    public Integer getPeso() {
        return peso;
    }

    /**
     * Para poner el peso
     * @param peso
     */
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    /**
     * Para recibir la altura
     * @return Integer
     */
    public Integer getAltura() {
        return altura;
    }

    /**
     * Para poner la altura
     * @param altura
     */
    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    /**
     * Para recibir la Imagen
     *
     * @return Integer
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Para poner la imagen
     * @param imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deportista that = (Deportista) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(sexo, that.sexo) && Objects.equals(peso, that.peso) && Objects.equals(altura, that.altura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, sexo, peso, altura);
    }
}
