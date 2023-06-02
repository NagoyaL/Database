/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_db.Modelo;

/**
 *
 * @author jpant
 */
public class LibroEditorial {
    private Libro libro;
    private Editorial editorial;

    // Constructor
    public LibroEditorial(Libro libro, Editorial editorial) {
        this.libro = libro;
        this.editorial = editorial;
    }

    // Getters y Setters
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
