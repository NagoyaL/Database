/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.AreaConocimiento;
import Logica.Libro;

public class DaoLibro {
    private Connection conexion;

    public DaoLibro(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO Libro (ISBN, titulo, anio_publicacion, idioma, area_conocimiento, num_paginas) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libro.getISBN());
            statement.setString(2, libro.getTitulo());
            statement.setInt(3, libro.getAnioPublicacion());
            statement.setString(4, libro.getIdioma());
            if (libro.getAreaConocimiento() != null) {
                statement.setString(5, libro.getAreaConocimiento().getCodigo());
            } else {
                statement.setNull(5, java.sql.Types.NULL);
            }
            statement.setInt(6, libro.getNumPaginas());

            statement.executeUpdate();
        }
    }

    public void actualizarLibro(Libro libro) throws SQLException {
        String query = "UPDATE Libro SET titulo = ?, anio_publicacion = ?, idioma = ?, area_conocimiento = ?, num_paginas = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libro.getTitulo());
            statement.setInt(2, libro.getAnioPublicacion());
            statement.setString(3, libro.getIdioma());
            if (libro.getAreaConocimiento() != null) {
                statement.setString(4, libro.getAreaConocimiento().getCodigo());
            } else {
                statement.setNull(4, java.sql.Types.NULL);
            }
            statement.setInt(5, libro.getNumPaginas());
            statement.setString(6, libro.getISBN());

            statement.executeUpdate();
        }
    }

    public void eliminarLibro(String ISBN) throws SQLException {
        String query = "DELETE FROM Libro WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ISBN);

            statement.executeUpdate();
        }
    }

    public Libro obtenerLibro(String ISBN) throws SQLException {
        String query = "SELECT * FROM Libro WHERE ISBN = ?";
        Libro libro = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ISBN);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String titulo = resultSet.getString("titulo");
                    int anioPublicacion = resultSet.getInt("anio_publicacion");
                    String idioma = resultSet.getString("idioma");
                    String codigoAreaConocimiento = resultSet.getString("area_conocimiento");
                    int numPaginas = resultSet.getInt("num_paginas");

                    AreaConocimiento areaConocimiento = null;
                    if (codigoAreaConocimiento != null) {
                        areaConocimiento = obtenerAreaConocimiento(codigoAreaConocimiento);
                    }

                    libro = new Libro(ISBN, titulo, anioPublicacion, idioma, areaConocimiento, numPaginas);
                }
            }
        }

        return libro;
    }

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        String query = "SELECT * FROM Libro";
        List<Libro> libros = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String ISBN = resultSet.getString("ISBN");
                String titulo = resultSet.getString("titulo");
                int anioPublicacion = resultSet.getInt("anio_publicacion");
                String idioma = resultSet.getString("idioma");
                String codigoAreaConocimiento = resultSet.getString("area_conocimiento");
                int numPaginas = resultSet.getInt("num_paginas");

                AreaConocimiento areaConocimiento = null;
                if (codigoAreaConocimiento != null) {
                    areaConocimiento = obtenerAreaConocimiento(codigoAreaConocimiento);
                }

                Libro libro = new Libro(ISBN, titulo, anioPublicacion, idioma, areaConocimiento, numPaginas);
                libros.add(libro);
            }
        }

        return libros;
    }

    private AreaConocimiento obtenerAreaConocimiento(String codigo) throws SQLException {
        String query = "SELECT * FROM AreaConocimiento WHERE codigo = ?";
        AreaConocimiento areaConocimiento = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    String codigoAreaPadre = resultSet.getString("codigo_area_padre");

                    AreaConocimiento areaPadre = null;
                    if (codigoAreaPadre != null) {
                        areaPadre = obtenerAreaConocimiento(codigoAreaPadre);
                    }

                    areaConocimiento = new AreaConocimiento(codigo, nombre, descripcion, areaPadre);
                }
            }
        }

        return areaConocimiento;
    }
}

