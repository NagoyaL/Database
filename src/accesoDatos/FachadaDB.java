package accesoDatos;

import java.sql.*;
public class FachadaDB {
        String url, usuario, password;
        Connection conexion =null;   
        
        public FachadaDB(){
            url="jdbc:postgresql://localhost:5432/Biblioteca";
            usuario="postgres";
            password="Nico0624!";
        }
        
        

        public Connection openConnection(){
            try {
            
            Class.forName("org.postgresql.Driver");
            
            } catch( ClassNotFoundException e ) {
                System.out.println( "No se pudo cargar el driver." );
            }

            try{
                     
                     conexion = DriverManager.getConnection(url, usuario, password);
                     System.out.println( "Conexion Exitosa con la Base de datos" );
                     return conexion;
                  
             } catch( SQLException e ) {
                System.out.println( "No se pudo abrir la bd." );
                return null;
             }

        }

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