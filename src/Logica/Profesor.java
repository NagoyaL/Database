
package Logica;

public class Profesor extends Usuario {
    private String dependencia;
    private String titulo;
    private String areasInteres;

    // Constructor
    public Profesor(String identificacion, String nombre, String apellido, String direccion, String telefono, String email, String tipo_usuario, String dependencia, String titulo, String areasInteres) {
        super(identificacion, nombre, apellido, direccion, telefono, email, tipo_usuario);
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
