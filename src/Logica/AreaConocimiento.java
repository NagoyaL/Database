/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

public class AreaConocimiento {
    private String codigo;
    private String nombre;
    private String descripcion;
    private AreaConocimiento areaPadre; 

    // Constructor
    public AreaConocimiento(String codigo, String nombre, String descripcion, AreaConocimiento areaPadre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.areaPadre = areaPadre;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AreaConocimiento getAreaPadre() {
        return areaPadre;
    }

    public void setAreaPadre(AreaConocimiento areaPadre) {
        this.areaPadre = areaPadre;
    }
}
