
package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Empleado;

public class DaoEmpleado {
    private Connection conexion;

    public DaoEmpleado(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO Empleado (identificacion, nombre, cargo) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, empleado.getIdentificacion());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getCargo());

            statement.executeUpdate();
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE Empleado SET nombre = ?, cargo = ? WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getCargo());
            statement.setString(3, empleado.getIdentificacion());

            statement.executeUpdate();
        }
    }

    public void eliminarEmpleado(String identificacion) throws SQLException {
        String query = "DELETE FROM Empleado WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);
            statement.executeUpdate();
        }
    }

    public Empleado obtenerEmpleado(String identificacion) throws SQLException {
        String query = "SELECT * FROM Empleado WHERE identificacion = ?";
        Empleado empleado = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String cargo = resultSet.getString("cargo");

                    empleado = new Empleado(identificacion, nombre, cargo);
                }
            }
        }

        return empleado;
    }

    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        String query = "SELECT * FROM Empleado";
        List<Empleado> empleados = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String identificacion = resultSet.getString("identificacion");
                String nombre = resultSet.getString("nombre");
                String cargo = resultSet.getString("cargo");

                Empleado empleado = new Empleado(identificacion, nombre, cargo);
                empleados.add(empleado);
            }
        }

        return empleados;
    }
}
