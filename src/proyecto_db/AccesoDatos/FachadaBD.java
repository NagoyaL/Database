/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_db.AccesoDatos;

/**
 *
 * @author gurui
 */

import java.sql.*;

public class FachadaBD {
        String url, usuario, password;
        Connection conexion;
        
        FachadaBD() {
            url = "jdbc:postgresql://localhost:5432/postgres";
            usuario = "postgres";
            password = "postgres";
        }
        
        
        public Connection openConnection() {
            try {
            // Se carga el driver
            Class.forName("org.postgresql.Driver");
            //System.out.println( "Driver Cargado" );
            } catch( ClassNotFoundException e ) {
                System.out.println( "No se pudo cargar el driver." );
            }

            try{
                     //Crear el objeto de conexion a la base de datos
                     conexion = DriverManager.getConnection(url, usuario, password);
                     System.out.println( "Conexion Exitosa con la Base de datos" );
                     return conexion;
                  //Crear objeto Statement para realizar queries a la base de datos
             } catch( SQLException e ) {
                System.out.println( "No se pudo abrir la bd." );
                return null;
             }

        }//end connectar
        
        public Connection getConnetion(){
            if (conexion == null) {
                return this.openConnection();
            }
            else{
                  return conexion;      
            }
            
        }
        
        public void closeConection(Connection c){
            try{
                if (conexion != null){
                    c.close();
                }
                 
            } catch( SQLException e ) {
                System.out.println( "No se pudo cerrar." );
            }
        }
        
}
