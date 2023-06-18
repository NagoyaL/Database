import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Editorial;

public class DaoEditorial {
    private Connection conexion;

    public DaoEditorial(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarEditorial(Editorial editorial) throws SQLException {
        String query = "INSERT INTO Editorial (codigo, nombre, pagina_web, pais_origen) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, editorial.getCodigo());
            statement.setString(2, editorial.getNombre());
            statement.setString(3, editorial.getPaginaWeb());
            statement.setString(4, editorial.getPaisOrigen());

            statement.executeUpdate();
        }
    }

    public void actualizarEditorial(Editorial editorial) throws SQLException {
        String query = "UPDATE Editorial SET nombre = ?, pagina_web = ?, pais_origen = ? WHERE codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, editorial.getNombre());
            statement.setString(2, editorial.getPaginaWeb());
            statement.setString(3, editorial.getPaisOrigen());
            statement.setString(4, editorial.getCodigo());

            statement.executeUpdate();
        }
    }

    public void eliminarEditorial(int codigo) throws SQLException {
        String query = "DELETE FROM Editorial WHERE codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }

    public Editorial obtenerEditorial(String codigo) throws SQLException {
        String query = "SELECT * FROM Editorial WHERE codigo = ?";
        Editorial editorial = null;

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String paginaWeb = resultSet.getString("pagina_web");
                    String paisOrigen = resultSet.getString("pais_origen");

                    editorial = new Editorial(codigo, nombre, paginaWeb, paisOrigen);
                }
            }
        }

        return editorial;
    }

    public List<Editorial> obtenerTodasLasEditoriales() throws SQLException {
        String query = "SELECT * FROM Editorial";
        List<Editorial> editoriales = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nombre = resultSet.getString("nombre");
                String paginaWeb = resultSet.getString("pagina_web");
                String paisOrigen = resultSet.getString("pais_origen");

                Editorial editorial = new Editorial(codigo, nombre, paginaWeb, paisOrigen);
                editoriales.add(editorial);
            }
        }

        return editoriales;
    }
}