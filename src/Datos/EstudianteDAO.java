
package Datos;

import Modelo.Estudiante;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EstudianteDAO implements IDAO{
    Connection con = null;
    ArrayList<Estudiante> lista = new ArrayList<>();
    Estudiante estudianteObj = null;
    PreparedStatement ps; 

    public EstudianteDAO() throws SQLException{
        con = new Conexion().conectar();
    }
    
    @Override
    public ArrayList listar(String condicion) {
        try{
            String sql = "SELECT * FROM Estudiante "+ condicion;
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                estudianteObj = new Estudiante(
                        rs.getInt("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipoUsuario"),
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
