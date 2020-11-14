

package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;


public class Fachada {
    private static Connection con = null;
    
    public static Connection startConnection(){
        try
        {
            if(con == null){
                //Determina cuando se termina el programa
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
                //Recupera los parámetros de conexión del archivo 
                //jdbc.properties
                //ResourceBundle rb = ResourceBundle.getBundle("servicios.jdbc");
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/proyectomaestrocracksupremo";
                String pwd = "";
                String usr = "root";
                
                Class.forName(driver);
                con = DriverManager.getConnection(url, usr, pwd);
            }
                         
        }
        catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return con;
    }
    
    static class MiShDwnHook extends Thread{
        //Justo antes de finaliza el programa la JVM invocará
        //este método donde podemos cerra la conexión
        @Override
        public void run(){
            try{
                Connection con = Fachada.startConnection();
                con.close();                     
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Error : " + 
                        ex.getMessage());
            }
        }
    }
}
