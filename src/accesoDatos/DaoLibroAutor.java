package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.LibroAutor;
import Logica.Libro;
import Logica.Autor;

public class DaoLibroAutor {
    private Connection conexion;

    public DaoLibroAutor(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearLibroAutor(LibroAutor libroAutor) throws SQLException {
        String query = "INSERT INTO Libro_Autor (ISBN, autor_codigo) VALUES (?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroAutor.getIsbn().getISBN());
            statement.setString(2, libroAutor.getAutorCodigo().getCodigo());

            statement.executeUpdate();
        }
    }

    public List<LibroAutor> obtenerLibrosAutores() throws SQLException {
        String query = "SELECT * FROM Libro_Autor";
        List<LibroAutor> librosAutores = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String autorCodigo = resultSet.getString("autor_codigo");

                DaoLibro libroDAO = new DaoLibro(conexion);
                DaoAutor autorDAO = new DaoAutor(conexion);

                Libro libro = libroDAO.obtenerLibro(isbn);
                Autor autor = autorDAO.obtenerAutor(autorCodigo);

                LibroAutor libroAutor = new LibroAutor(libro, autor);
                librosAutores.add(libroAutor);
            }
        }

        return librosAutores;
    }

    public void actualizarLibroAutor(LibroAutor libroAutor) throws SQLException {
        String query = "UPDATE Libro_Autor SET autor_codigo = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroAutor.getAutorCodigo().getCodigo());
            statement.setString(2, libroAutor.getIsbn().getISBN());

            statement.executeUpdate();
        }
    }

    public void eliminarLibroAutor(LibroAutor libroAutor) throws SQLException {
        String query = "DELETE FROM Libro_Autor WHERE ISBN = ? AND autor_codigo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, libroAutor.getIsbn().getISBN());
            statement.setString(2, libroAutor.getAutorCodigo().getCodigo());

            statement.executeUpdate();
        }
    }
}
