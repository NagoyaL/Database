
package Controlador;
import accesoDatos.IDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import accesoDatos.DaoMulta;
import accesoDatos.IDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MultaControl implements IDAO{

    @Override
    public ArrayList listar(String condicion) {
        try {
            return new DaoMulta().listar(condicion);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertar(Object obj) {
        try {
            new DaoMulta().insertar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void modificar(Object obj) {
        try {
            new DaoMulta().modificar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void eliminar(Object obj) {
        try {
            new DaoMulta().eliminar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());  
        }
    }
    
}