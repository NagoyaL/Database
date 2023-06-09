
package Modelo;

public class Estudiante extends Usuario {
    private String carrera;
    private String universidad;

    // Constructor
    public Estudiante(int identificacion, String nombre, String direccion, String telefono, String email, String tipoUsuario, String carrera, String universidad) {
        super(identificacion, nombre, direccion, telefono, email, tipoUsuario);
        this.carrera = carrera;
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }


}
