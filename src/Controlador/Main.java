/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;
 
/**
 *
 * @author user
 */
import java.sql.Connection;
import accesoDatos.FachadaDB;
import Vista.vistaLogin;

public class Main {
    public static void main(String[] args) {
        FachadaDB fachadaDB = new FachadaDB();
        Connection connection = fachadaDB.openConnection();

        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos");
            // Aquí puedes realizar otras operaciones con la base de datos
            // ...
            
            // Cerrar la conexión cuando ya no se necesite
            fachadaDB.closeConection(connection);
        } else {
            System.out.println("Error al conectar a la base de datos");
        }

        vistaLogin loginForm = new vistaLogin();
        loginForm.setVisible(true);
    }
}


