package db;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ruben Angeles
 */
public class Conexion {
    private final String db = "ABC";
    private final String url = "jdbc:mysql://192.168.40.15/"+db;
    private final String user = "consultas";
    private final String pass = "soportemx";
    private Connection link = null;
    public Connection conectar(){
        //Connection link = null;
        try{
            //System.out.println("Conectando a DB");
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            //System.out.println("Conexion a DB Establecida");
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error en la Conexión");
       }
       return link;
    }
    public Connection conectar(String IP,String DataBase,String user,String pass){
        //Connection link = null;
        String dir = "jdbc:mysql://"+IP+"/"+DataBase;
        try{
            //System.out.println("Conectando a DB");
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(dir, user, pass);
            //System.out.println("Conexion a DB Establecida");
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error en la Conexión");
       }
       return link;
    }
    public ResultSet consulta(String consulta) throws SQLException{
        Statement s = link.createStatement();
        ResultSet rs = s.executeQuery(consulta);
        //conectar().close();
        return rs;
    }
    public void insertar(String query) throws SQLException{
        //System.out.println("Preparando el Query");
        Statement s = link.createStatement();
        s.executeUpdate(query);
        //System.out.println("Query ejecutado con Exito");
    }
    public void update(String query) throws SQLException{
        //System.out.println("Preparando el Query");
        Statement s = link.createStatement();
        s.executeUpdate(query);
        //System.out.println("Query ejecutado con Exito");
    }
    public void desconectar() throws SQLException{
        link.close();
    }
}