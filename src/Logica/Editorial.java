
package Logica;

public class Editorial {
    private String codigo;
    private String nombre;
    private String paginaWeb;
    private String paisOrigen;

    // Constructor
    public Editorial(String codigo, String nombre, String paginaWeb, String paisOrigen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.paginaWeb = paginaWeb;
        this.paisOrigen = paisOrigen;
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

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
