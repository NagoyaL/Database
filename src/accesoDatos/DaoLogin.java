/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDatos;

/**
 *
 * @author jpant
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Login;

public class DaoLogin {
    private Connection conexion;

    public DaoLogin(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean validarCredenciales(String usuario, String contrasena) throws SQLException {
    String query = "SELECT COUNT(*) FROM login WHERE username = ? AND password = ?;";

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, usuario);
        statement.setString(2, contrasena);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Devuelve true si se encontraron registros, es decir, las credenciales son válidas
            }
        }
    }

    return false; // Si no se encontraron registros, las credenciales no son válidas
    }
    
    public boolean esAdministrador(String username) {
    String query = "SELECT role FROM login WHERE username = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, username);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                return role.equals("administrador");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false;  // Usuario no encontrado o error en la consulta
    }

    
    public void crearLogin(Login login) throws SQLException {
        String query = "INSERT INTO login (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());
            statement.setString(3, login.getRole());

            statement.executeUpdate();
        }
    }

    public Login obtenerLogin(String username) throws SQLException {
        String query = "SELECT * FROM login WHERE username = ?";
        Login login = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");

                    login = new Login(username, password, role);
                }
            }
        }

        return login;
    }

    public void actualizarLogin(Login login) throws SQLException {
        String query = "UPDATE login SET password = ?, role = ? WHERE username = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, login.getPassword());
            statement.setString(2, login.getRole());
            statement.setString(3, login.getUsername());

            statement.executeUpdate();
        }
    }

    public void eliminarLogin(String username) throws SQLException {
        String query = "DELETE FROM login WHERE username = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, username);

            statement.executeUpdate();
        }
    }

}
