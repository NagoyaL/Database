package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.LibroEditorial;
import Logica.Libro;
import Logica.Editorial;


public class DaoLibroEditorial {
    private Connection conexion;

    public DaoLibroEditorial(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearLibroEditorial(LibroEditorial libroEditorial) throws SQLException {
        String query = "INSERT INTO Libro_Editorial (ISBN, editorial_codigo) VALUES (?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroEditorial.getLibro().getISBN());
            statement.setString(2, libroEditorial.getEditorial().getCodigo());

            statement.executeUpdate();
        }
    }

    public List<LibroEditorial> obtenerLibrosEditoriales() throws SQLException {
        String query = "SELECT * FROM Libro_Editorial";
        List<LibroEditorial> librosEditoriales = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String editorialCodigo = resultSet.getString("editorial_codigo");

                DaoLibro libroDAO = new DaoLibro(conexion);
                Libro libro = libroDAO.obtenerLibro(isbn);

                DaoEditorial DaoEditorial = new DaoEditorial(conexion);
                Editorial editorial = DaoEditorial.obtenerEditorial(editorialCodigo);

                LibroEditorial libroEditorial = new LibroEditorial(libro, editorial);
                librosEditoriales.add(libroEditorial);
            }
        }

        return librosEditoriales;
    }

    public void actualizarLibroEditorial(LibroEditorial libroEditorial) throws SQLException {
        String query = "UPDATE Libro_Editorial SET editorial_codigo = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroEditorial.getEditorial().getCodigo());
            statement.setString(2, libroEditorial.getLibro().getISBN());

            statement.executeUpdate();
        }
    }

    public void eliminarLibroEditorial(LibroEditorial libroEditorial) throws SQLException {
        String query = "DELETE FROM Libro_Editorial WHERE ISBN = ? AND editorial_codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroEditorial.getLibro().getISBN());
            statement.setString(2, libroEditorial.getEditorial().getCodigo());

            statement.executeUpdate();
        }
    }
}
