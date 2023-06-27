
package Controlador;
import accesoDatos.DaoEstudiante;
import accesoDatos.IDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EstudianteControl implements IDAO{

    @Override
    public ArrayList listar(String condicion) {
        try {
            return new DaoEstudiante().listar(condicion);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertar(Object obj) {
        try {
            new DaoEstudiante().insertar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void modificar(Object obj) {
        try {
            new DaoEstudiante().modificar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void eliminar(Object obj) {
        try {
            new DaoEstudiante().eliminar(obj);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());  
        }
    }
    
}
