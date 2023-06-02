/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_db.Modelo;

/**
 *
 * @author jpant
 */
public class LibroAutor {
    private Libro isbn;
    private Autor autorCodigo;

    // Constructor
    public LibroAutor(Libro isbn, Autor autorCodigo) {
        this.isbn = isbn;
        this.autorCodigo = autorCodigo;
    }

    // Getters y Setters
    public Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(Libro isbn) {
        this.isbn = isbn;
    }

    public Autor getAutorCodigo() {
        return autorCodigo;
    }

    public void setAutorCodigo(Autor autorCodigo) {
        this.autorCodigo = autorCodigo;
    }
}
