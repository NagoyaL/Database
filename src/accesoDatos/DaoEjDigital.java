package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.EjDigital;
import Logica.Libro;

public class DaoEjDigital {
    private Connection conexion;

    public DaoEjDigital(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearEjDigital(EjDigital ejDigital) throws SQLException {
        String query = "INSERT INTO Libro_Digital (ISBN, url, formato_archivo, tamanio_bytes) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ejDigital.getIsbn());
            statement.setString(2, ejDigital.getUrl());
            statement.setString(3, ejDigital.getFormatoArchivo());
            statement.setInt(4, ejDigital.getTamanoBytes());

            statement.executeUpdate();
        }
    }

    public List<EjDigital> obtenerEjemplaresDigitales(EjDigital ejDigitalISBN) throws SQLException {
    String query = "SELECT * FROM Libro_Digital WHERE ISBN = ?";
    List<EjDigital> ejemplaresDigitales = new ArrayList<>();

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, ejDigitalISBN.getIsbn());

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String url = resultSet.getString("url");
                String formatoArchivo = resultSet.getString("formato_archivo");
                int tamanoBytes = resultSet.getInt("tamanio_bytes");

                DaoLibro libroDAO = new DaoLibro(conexion);
                Libro libro = libroDAO.obtenerLibro(isbn);

                EjDigital ejDigital = new EjDigital(isbn, url, formatoArchivo, tamanoBytes, libro);
                ejemplaresDigitales.add(ejDigital);
            }
        }
    }

    return ejemplaresDigitales;
}

    public void actualizarEjDigital(EjDigital ejDigital) throws SQLException {
        String query = "UPDATE Libro_Digital SET url = ?, formato_archivo = ?, tamanio_bytes = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ejDigital.getUrl());
            statement.setString(2, ejDigital.getFormatoArchivo());
            statement.setInt(3, ejDigital.getTamanoBytes());
            statement.setString(4, ejDigital.getIsbn());

            statement.executeUpdate();
        }
    }

    public void eliminarEjDigital(EjDigital ejDigital) throws SQLException {
        String query = "DELETE FROM Libro_Digital WHERE ISBN = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ejDigital.getIsbn());

            statement.executeUpdate();
        }
    }
}