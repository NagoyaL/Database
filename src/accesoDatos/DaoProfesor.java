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
import Logica.Profesor;

public class DaoProfesor {
    private Connection conexion;

    public DaoProfesor(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProfesor(Profesor profesor) throws SQLException {
        String query = "INSERT INTO Profesor (identificacion, dependencia, titulo, areas_interes) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, profesor.getIdentificacion());
            statement.setString(2, profesor.getDependencia());
            statement.setString(3, profesor.getTitulo());
            statement.setString(4, profesor.getAreasInteres());

            statement.executeUpdate();
        }
    }

    public void actualizarProfesor(Profesor profesor) throws SQLException {
        String query = "UPDATE Profesor SET dependencia = ?, titulo = ?, areas_interes = ? WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, profesor.getDependencia());
            statement.setString(2, profesor.getTitulo());
            statement.setString(3, profesor.getAreasInteres());
            statement.setString(4, profesor.getIdentificacion());

            statement.executeUpdate();
        }
    }

    public void eliminarProfesor(String identificacion) throws SQLException {
        String query = "DELETE FROM Profesor WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);
            statement.executeUpdate();
        }
    }

        public Profesor obtenerProfesor(String identificacion) throws SQLException {
        String query = "SELECT * FROM Profesor WHERE identificacion = ?";
        Profesor profesor = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String email = resultSet.getString("email");
                    String tipoUsuario = resultSet.getString("tipo_usuario");
                    String dependencia = resultSet.getString("dependencia");
                    String titulo = resultSet.getString("titulo");
                    String areasInteres = resultSet.getString("areas_interes");

                    profesor = new Profesor(identificacion, nombre, direccion, telefono, email, dependencia, titulo, areasInteres);
                }
            }
        }

        return profesor;
    }

    public List<Profesor> obtenerTodosLosProfesores() throws SQLException {
        String query = "SELECT * FROM Profesor";
        List<Profesor> profesores = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String identificacion = resultSet.getString("identificacion");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String dependencia = resultSet.getString("dependencia");
                String titulo = resultSet.getString("titulo");
                String areasInteres = resultSet.getString("areas_interes");

                Profesor profesor = new Profesor(identificacion, nombre, direccion, telefono, email,dependencia , titulo, areasInteres);
                profesores.add(profesor);
            }
        }

        return profesores;
    }
}
