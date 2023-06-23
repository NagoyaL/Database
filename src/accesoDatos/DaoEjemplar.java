package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Ejemplar;
import Logica.Libro;

public class DaoEjemplar {
    private Connection conexion;

    public DaoEjemplar(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearEjemplar(Ejemplar ejemplar) throws SQLException {
        String query = "INSERT INTO Ejemplar (numero, ISBN, sala, pasillo, estante, cajon) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, ejemplar.getNumero());
            statement.setString(2, ejemplar.getLibro().getISBN());
            statement.setString(3, ejemplar.getSala());
            statement.setInt(4, ejemplar.getPasillo());
            statement.setInt(5, ejemplar.getEstante());
            statement.setInt(6, ejemplar.getCajon());

            statement.executeUpdate();
        }
    }

    public List<Ejemplar> obtenerEjemplares() throws SQLException {
        String query = "SELECT * FROM Ejemplar";
        List<Ejemplar> ejemplares = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String isbn = resultSet.getString("ISBN");
                String sala = resultSet.getString("sala");
                int pasillo = resultSet.getInt("pasillo");
                int estante = resultSet.getInt("estante");
                int cajon = resultSet.getInt("cajon");

                DaoLibro DaoLibro = new DaoLibro(conexion);
                Libro libro = DaoLibro.obtenerLibro(isbn);

                Ejemplar ejemplar = new Ejemplar(numero, libro, sala, pasillo, estante, cajon);
                ejemplares.add(ejemplar);
            }
        }

        return ejemplares;
    }

    public void actualizarEjemplar(Ejemplar ejemplar) throws SQLException {
        String query = "UPDATE Ejemplar SET sala = ?, pasillo = ?, estante = ?, cajon = ? WHERE numero = ? AND ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ejemplar.getSala());
            statement.setInt(2, ejemplar.getPasillo());
            statement.setInt(3, ejemplar.getEstante());
            statement.setInt(4, ejemplar.getCajon());
            statement.setInt(5, ejemplar.getNumero());
            statement.setString(6, ejemplar.getLibro().getISBN());

            statement.executeUpdate();
        }
    }

    public void eliminarEjemplar(Ejemplar ejemplar) throws SQLException {
        String query = "DELETE FROM Ejemplar WHERE numero = ? AND ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, ejemplar.getNumero());
            statement.setString(2, ejemplar.getLibro().getISBN());

            statement.executeUpdate();
        }
    }
}