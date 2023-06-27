package accesoDatos;

import Logica.Estudiante;
import Logica.Profesor;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DaoProfesor implements IDAO{
    Connection con = null;
    ArrayList<Profesor> lista = new ArrayList<>();
    Profesor profesorObj = null;
    Estudiante estudianteObj = null;
    PreparedStatement ps;
    
    public DaoProfesor() throws SQLException{
        FachadaDB fachada = new FachadaDB();
        con = fachada.getConnetion();
    }
    
    @Override
    public ArrayList listar(String condicion) {
        try{
            String sql = "SELECT DISTINCT u.identificacion, u.nombre, u.apellido, u.direccion, u.telefono, u.email, u.tipo_usuario, e.carrera, e.universidad"
                    + " FROM Estudiante AS e, Usuario AS u " + "WHERE e.identificacion = u.identificacion " +condicion;
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                estudianteObj = new Estudiante(
                        rs.getString("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_usuario"),
                        rs.getString("carrera"),
                        rs.getString("universidad")
                );
                lista.add(estudianteObj);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());  
        }
        return lista;
    }

    @Override
    public void insertar(Object obj) {
        try {
            estudianteObj = (Estudiante) obj;
            String sql = "INSERT INTO Usuario (identificacion, nombre, apellido, direccion, telefono, email, tipo_usuario) "
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, estudianteObj.getIdentificacion());
            ps.setString(2, estudianteObj.getNombre());
            ps.setString(2, estudianteObj.getApellido());
            ps.setString(3, estudianteObj.getDireccion());
            ps.setString(4, estudianteObj.getTelefono());
            ps.setString(5, estudianteObj.getEmail());
            ps.setString(6, estudianteObj.getTipoUsuario());
            ps.executeUpdate();

            String sql2 = "INSERT INTO Estudiante (identificacion, carrera, universidad) "
                    + "VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql2);
            ps.setString(1, estudianteObj.getIdentificacion());
            ps.setString(2, estudianteObj.getCarrera());
            ps.setString(3, estudianteObj.getUniversidad());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void modificar(Object obj) {
        try {
            estudianteObj = (Estudiante) obj;
            
            String sql = "UPDATE Usuario SET nombre = ?,  apellido = ?, direccion = ?, telefono = ?, email = ?, tipo_usuario"
                    + " WHERE identificacion = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, estudianteObj.getNombre());
            ps.setString(2, estudianteObj.getApellido());
            ps.setString(3, estudianteObj.getDireccion());
            ps.setString(4, estudianteObj.getTelefono());
            ps.setString(5, estudianteObj.getEmail());
            ps.setString(6, estudianteObj.getTipoUsuario());
            String identificacionLong = estudianteObj.getIdentificacion();
            String identificacionStr = String.valueOf(identificacionLong);
            ps.setString(7, identificacionStr);
            ps.executeUpdate();
            
            String sql2 = "UPDATE Estudiante SET carrera = ?,  universidad = ?"
                    + " WHERE identificacion = ?";
            ps = con.prepareStatement(sql2);
            ps.setString(1, estudianteObj.getCarrera());
            ps.setString(2, estudianteObj.getUniversidad());
            ps.setString(3, identificacionStr);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void eliminar(Object obj) {
        try {
            estudianteObj = (Estudiante) obj;

            String sql = "DELETE FROM Estudiante WHERE identificacion = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,  estudianteObj.getIdentificacion());
            ps.executeUpdate();
            
            String sql2 = "DELETE FROM Usuario WHERE identificacion = ?";
            ps = con.prepareStatement(sql2);
            ps.setString(1,  estudianteObj.getIdentificacion());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
      }
    
    }
