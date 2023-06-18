package accesoDatos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Usuario;


public class DaoUsuario {
    private Connection conexion;

    public DaoUsuario(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO Usuario (identificacion, nombre, direccion, telefono, email)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getIdentificacion());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getDireccion());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getEmail());
            

            statement.executeUpdate();
        }
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE Usuario SET nombre = ?, direccion = ?, telefono = ?, email = ?"
                + "WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getDireccion());
            statement.setString(3, usuario.getTelefono());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getIdentificacion());

            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int identificacion) throws SQLException {
        String query = "DELETE FROM Usuario WHERE identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, identificacion);
            statement.executeUpdate();
        }
    }

    public Usuario obtenerUsuario(String identificacion) throws SQLException {
        String query = "SELECT * FROM Usuario WHERE identificacion = ?";
        Usuario usuario = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, identificacion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String email = resultSet.getString("email");
                    

                    usuario = new Usuario(identificacion, nombre, direccion, telefono, email);
                }
            }
        }

        return usuario;
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        String query = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String identificacion = resultSet.getString("identificacion");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");
                

                Usuario usuario = new Usuario(identificacion, nombre, direccion, telefono, email) {};
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }
}
