package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Descarga;
import Logica.Usuario;
import Logica.EjDigital;

public class DaoDescarga {
    private Connection conexion;

    public DaoDescarga(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearDescarga(Descarga descarga) throws SQLException {
        String query = "INSERT INTO Descarga (id, usuario_identificacion, ISBN, fecha, hora, ip) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, descarga.getId());
            statement.setString(2, descarga.getUsuario().getIdentificacion());
            statement.setString(3, descarga.getISBN().getIsbn());
            statement.setDate(4, new java.sql.Date(descarga.getFecha().getTime()));
            statement.setTime(5, descarga.getHora());
            statement.setString(6, descarga.getIp());

            statement.executeUpdate();
        }
    }

    public List<Descarga> obtenerDescargas() throws SQLException {
        String query = "SELECT * FROM Descarga";
        List<Descarga> descargas = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String usuarioIdentificacion = resultSet.getString("usuario_identificacion");
                String isbn = resultSet.getString("ISBN");
                java.sql.Date fecha = resultSet.getDate("fecha");
                java.sql.Time hora = resultSet.getTime("hora");
                String ip = resultSet.getString("ip");

                DaoUsuario DaoUsuario = new DaoUsuario(conexion);
                Usuario usuario = DaoUsuario.obtenerUsuario(usuarioIdentificacion);

                DaoEjDigital ejDigitalDAO = new DaoEjDigital(conexion);
                    EjDigital ejDigitalISBN = new EjDigital(isbn, null, null, 0, null);
                    List<EjDigital> ejemplaresDigitales = ejDigitalDAO.obtenerEjemplaresDigitales(ejDigitalISBN);
                    EjDigital ejDigital = ejemplaresDigitales.get(0);

                    Descarga descarga = new Descarga(id, usuario, ejDigital, fecha, hora, ip);
                    descargas.add(descarga);    
            }
        }

        return descargas;
    }

    public void actualizarDescarga(Descarga descarga) throws SQLException {
        String query = "UPDATE Descarga SET usuario_identificacion = ?, ISBN = ?, fecha = ?, hora = ?, ip = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, descarga.getUsuario().getIdentificacion());
            statement.setString(2, descarga.getISBN().getIsbn());
            statement.setDate(3, new java.sql.Date(descarga.getFecha().getTime()));
            statement.setTime(4, descarga.getHora());
            statement.setString(5, descarga.getIp());
            statement.setString(6, descarga.getId());

            statement.executeUpdate();
        }
    }

    public void eliminarDescarga(Descarga descarga) throws SQLException {
        String query = "DELETE FROM Descarga WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, descarga.getId());

            statement.executeUpdate();
        }
    }
}