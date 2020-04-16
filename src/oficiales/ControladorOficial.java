
package oficiales;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author EmilyBC
 */
public class ControladorOficial {
    private Connection conexion;
    private Statement sentencias;
    private ResultSet datos;
    PreparedStatement sentencia;
    ArrayList<Oficiales> oficiales = new ArrayList<>();
    
    
    double sal10;
    double sal15; 
    double de15;
    String total;
    double sal1;
    
    public void conectar(){
        try {
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/licencias?useServerPrepStmts=true", "root", "");
            this.sentencias = this.conexion.createStatement();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("Error al conectar");
        }
    }
    
      public boolean agregar(Oficiales of) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            this.sentencias.executeUpdate("insert into oficiales values("+ of.getNcedula() +" ,'" + of.getNombreC() + "','" + formato.format(of.getFnacimiento()) + "'," +of.getTeléfono() + ",'" +of.getCelectrónico() + "'," +of.getSalario() + ",'" + of.getTipo()+"')");
            System.out.println("Datos agregados exitosamente");
            return true;
        } catch (SQLException ex) {
            System.out.println("Datos NO agregados");
            return false;
        }
    }
      
    public boolean actualizar(Oficiales of,int tele, String correo, String nom, int salario ){
        try {
            sentencia = conexion.prepareStatement("select * from oficiales where Cedula = ?");
            sentencia.setInt(1, of.getNcedula());
            datos =sentencia.executeQuery();
            if (datos.next()) {
                of  = new Oficiales(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4), datos.getString(5), datos.getInt(6), datos.getString(7));
                this.sentencias.executeUpdate("update oficiales set Telefono= '"+tele+"',Correo='"+correo+"',NombreC='"+nom+"',Salario='"+salario+"' where Cedula="+of.getNcedula());
                return true;   
            }
        }catch (SQLException ex){
            System.out.println("No se ppudo actualizar");;
        }
        return false;
    }
      
         
    public boolean eliminar(Oficiales of){
        try {
            sentencia = conexion.prepareStatement("select * from oficiales where Cedula = ?");
            sentencia.setInt(1, of.getNcedula());
            datos =sentencia.executeQuery();
            if (datos.next()) {
                of = new Oficiales(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4), datos.getString(5), datos.getInt(6), datos.getString(7));
                sentencia =  conexion.prepareStatement("delete from oficiales where cedula="+of.getNcedula());
                sentencia.executeUpdate();
            }
            return true;
        } catch (SQLException ex){
            return false;
        }   
    }  
     
    public ArrayList Buscar(String descripcion){
        try {
           sentencia = conexion.prepareStatement("select * from oficiales where descripción like %'"+descripcion.trim() +"%'");
           datos= sentencia.executeQuery();
           while (datos.next()) {
                Oficiales ofi = new Oficiales(datos.getInt(1), datos.getString(2), datos.getDate(3), datos.getInt(4), datos.getString(5), datos.getInt(6), datos.getString(7));
                oficiales.add(ofi);      
            }
        } catch (SQLException ex) {
        }
        return oficiales;
    }
    
    public String deduccuiones(double salario){
        //Enfermedad y maternidad 5.5%.
        double por_em = 5.5 /100;
        double EM = salario * por_em;
        
        //Invalidez y Muerte 3.84%.
        double por_im = 3.84/100;
        double IM = salario * por_im;
        
        //Aporte del trabajador 1%.
        double por_at = 0.01;
        double AT = salario * por_at;
        
        //Aporte a la asociación solidarista 3.3%.
        double por_as = 3.3/100;
        double AS = salario * por_as;
         
        // renta 10%
        
        if (salario > 817001 && salario < 1226000 ) {
            sal1 = (salario -817001)*0.1;
        }

        // salario 15%
        if (salario >= 1226001) {
            sal10= (1226000-817001)*0.1;
            sal15 = (salario -1226001)*0.15;
            de15= sal10+sal15;
        }

        //deducciones 
        double totalD1= EM+IM+AT+AS+sal1+de15;
      
        // neto
        double totalS1 = salario -totalD1;
        
        String info = " Dedupciones por: Enfermedad y maternidad= "+EM+"Invalidez y Muerte= "+IM+"Apote del trabajador="+AT+
        "Aporte a la asociación solidarista= "+AS+"Renta 10%= "+sal1+"Renta 15%= "+de15+ "Sario Neto= "+totalD1;
        return info;

    }
    
}

