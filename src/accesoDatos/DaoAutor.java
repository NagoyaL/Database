package accesoDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Autor;

public class DaoAutor{
    private Connection conexion;

    public DaoAutor(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarAutor(Autor autor) throws SQLException {
        String query = "INSERT INTO Autor (codigo, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, autor.getCodigo());
            statement.setString(2, autor.getPrimerNombre());
            statement.setString(3, autor.getSegundoNombre());
            statement.setString(4, autor.getPrimerApellido());
            statement.setString(5, autor.getSegundoApellido());

            statement.executeUpdate();
        }
    }

    public void actualizarAutor(Autor autor) throws SQLException {
        String query = "UPDATE Autor SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ? "
                + "WHERE codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, autor.getPrimerNombre());
            statement.setString(2, autor.getSegundoNombre());
            statement.setString(3, autor.getPrimerApellido());
            statement.setString(4, autor.getSegundoApellido());
            statement.setString(5, autor.getCodigo());

            statement.executeUpdate();
        }
    }

    public void eliminarAutor(int codigo) throws SQLException {
        String query = "DELETE FROM Autor WHERE codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }

    public Autor obtenerAutor(String codigo) throws SQLException {
        String query = "SELECT * FROM Autor WHERE codigo = ?";
        Autor autor = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String primerNombre = resultSet.getString("primer_nombre");
                    String segundoNombre = resultSet.getString("segundo_nombre");
                    String primerApellido = resultSet.getString("primer_apellido");
                    String segundoApellido = resultSet.getString("segundo_apellido");

                    autor = new Autor(codigo, primerNombre, segundoNombre, primerApellido, segundoApellido);
                }
            }
        }

        return autor;
    }

    public List<Autor> obtenerTodosLosAutores() throws SQLException {
        String query = "SELECT * FROM Autor";
        List<Autor> autores = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String primerNombre = resultSet.getString("primer_nombre");
                String segundoNombre = resultSet.getString("segundo_nombre");
                String primerApellido = resultSet.getString("primer_apellido");
                String segundoApellido = resultSet.getString("segundo_apellido");

                Autor autor = new Autor(codigo, primerNombre, segundoNombre, primerApellido, segundoApellido);
                autores.add(autor);
            }
        }

        return autores;
    }
}