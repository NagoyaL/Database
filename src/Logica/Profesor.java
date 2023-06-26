/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

public class Profesor extends Usuario {
    private String dependencia;
    private String titulo;
    private String areasInteres;

    // Constructor
    public Profesor(String identificacion, String nombre, String direccion, String telefono, String email, String dependencia, String titulo, String areasInteres) {
        super(identificacion, nombre, direccion, telefono, email);
        this.dependencia = dependencia;
        this.titulo = titulo;
        this.areasInteres = areasInteres;
    }

    // Getters y setters específicos de Profesor
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
}