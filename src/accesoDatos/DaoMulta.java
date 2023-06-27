
package accesoDatos;

import Logica.Multa;
import Logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DaoMulta implements IDAO{
    Connection con = null;
    ArrayList<Multa> lista = new ArrayList<>();
    Multa multaObj = null;
    PreparedStatement ps;
    
    public DaoMulta() throws SQLException{
        FachadaDB fachada = new FachadaDB();
        con = fachada.getConnetion();
    }
    
    @Override
    public ArrayList listar(String condicion) {
        try{
            
            String sql = "SELECT m.*" + " FROM Multa m" + "INNER JOIN Usuario u ON m.usuario_identificacion = u.identificacion" +
                    "WHERE u.tipo_usuario = 'Estudiante'" + condicion;
            
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario(rs.getString("usuario_identificacion"));
                multaObj = new Multa(
                        rs.getInt("numero"),
                        usuario,
                        rs.getDate("fecha"),
                        rs.getDouble("valor"),
                        rs.getString("descripcion") 
                );
                lista.add(multaObj);
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
