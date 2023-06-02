/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_db.Modelo;

/**
 *
 * @author gurui
 */
public class Profesor {
    private int identificacion;
    private String dependencia;
    private String titulo;
    private String areasInteres;
    private Usuario usuario; // Representa la clave externa

    // Constructor
    public Profesor(int identificacion, String dependencia, String titulo, String areasInteres, Usuario usuario) {
        this.identificacion = identificacion;
        this.dependencia = dependencia;
        this.titulo = titulo;
        this.areasInteres = areasInteres;
        this.usuario = usuario;
    }

    // Getters y Setters
    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAreasInteres() {
        return areasInteres;
    }

    public void setAreasInteres(String areasInteres) {
        this.areasInteres = areasInteres;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
