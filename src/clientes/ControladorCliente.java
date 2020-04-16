
package clientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jazmine
 */
public class ControladorCliente {
    private Connection conexion;
    private Statement sentencias;
    private ResultSet datos;
    PreparedStatement sentencia;
    ArrayList<Clientes> clientes = new ArrayList<>();
    
      public void conectar(){
        try {
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/modules?useServerPrepStmts=true", "root", "");
            this.sentencias = this.conexion.createStatement();
            System.out.println("Conexion a la BD exitosa");
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la BD");
        }
                
    }
      
    public void agregar(Clientes cliente){
        
        try{
            this.sentencias.executeUpdate("insert into clientes values("+ cliente.getCedula() +" ,'"+ cliente.getNombreC() +"','"+cliente.getFnacimiento()+"',"+ cliente.getTelefono() +",'"+ cliente.getCorreo() +"')");
            
            System.out.println("Datos agregados con exito");
        }catch (SQLException ex) {
            System.out.println("Error al agregar los datos");
        }
        
    }
    
    public void actualizar(Clientes cliente, int cd, String n, Date fecha, int telf, String email) {
        try {
            sentencia = conexion.prepareStatement("select * from clientes where Cedula = ?");
            sentencia.setInt(1, cliente.getCedula());
            datos = sentencia.executeQuery();
            if (datos.next()) {
                Clientes cli = new Clientes(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4), datos.getString(5));
                sentencia = conexion.prepareStatement("update clientes set Cedula=?, NombreC=?,Fnacimiento=?, Telefono=?,"
                        + "Correo=?,where Cedula=" + cliente.getCedula() + "");
                sentencia.setInt(1, cd);
                sentencia.setString(1, n);
                sentencia.setDate(1, fecha);
                sentencia.setInt(1, telf);
                sentencia.setString(1, email);
                sentencia.executeUpdate();
                System.out.println("Datos actualizados");
            }

        } catch (SQLException ex) {
            System.out.println("Datos no actualizados");
        }

    }
    
      public void eliminar(Clientes cliente) {
        try {
            sentencia = conexion.prepareStatement("select * from clientes where Cedula = ?");
            sentencia.setInt(1, cliente.getCedula());
            datos = sentencia.executeQuery();
            if (datos.next()) {
                Clientes cli= new Clientes(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4), datos.getString(5));
                sentencia = conexion.prepareStatement("delete from clientes where id=" + cliente.getCedula());
                sentencia.executeUpdate();
                System.out.println("Datos eliminados");
            }

        } catch (SQLException ex) {
          System.out.println("Datos no eliminados");
        }  
    }
      
    public ArrayList buscar(int ced) {
        try {
            sentencia = conexion.prepareStatement("select * from clientes where Cedula like %'" + ced + "%'");
            datos = sentencia.executeQuery();
            while (datos.next()) {
                Clientes cli = new Clientes(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4),
                datos.getString(5));
                clientes.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return clientes;
    }
    
 
}
              
      
   
