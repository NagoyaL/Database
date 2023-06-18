package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Estudiante;


public class DaoEstudiante {
    private Connection conexion;

    public DaoEstudiante(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "INSERT INTO Estudiante (identificacion, carrera, universidad) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, estudiante.getIdentificacion());
            statement.setString(2, estudiante.getCarrera());
            statement.setString(3, estudiante.getUniversidad());

            statement.executeUpdate();
        }
    }

    public void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "UPDATE Estudiante SET carrera = ?, universidad = ? WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, estudiante.getCarrera());
            statement.setString(2, estudiante.getUniversidad());
            statement.setString(3, estudiante.getIdentificacion());

            statement.executeUpdate();
        }
    }

    public void eliminarEstudiante(String identificacion) throws SQLException {
        String query = "DELETE FROM Estudiante WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);
            statement.executeUpdate();
        }
    }

    public Estudiante obtenerEstudiante(String identificacion) throws SQLException {
        String query = "SELECT * FROM Estudiante WHERE identificacion = ?";
        Estudiante estudiante = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String email = resultSet.getString("email");
                    String tipoUsuario = resultSet.getString("tipo_usuario");
                    String carrera = resultSet.getString("carrera");
                    String universidad = resultSet.getString("universidad");

                    estudiante = new Estudiante(identificacion, nombre, direccion, telefono, email, carrera, universidad);
                }
            }
        }

        return estudiante;
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() throws SQLException {
        String query = "SELECT * FROM Estudiante";
        List<Estudiante> estudiantes = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String identificacion = resultSet.getString("identificacion");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String carrera = resultSet.getString("carrera");
                String universidad = resultSet.getString("universidad");

                Estudiante estudiante = new Estudiante(identificacion, nombre, direccion, telefono, email, carrera, universidad);
                estudiantes.add(estudiante);
            }
        }

        return estudiantes;
    }


}