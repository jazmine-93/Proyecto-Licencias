
package clientes;

import java.util.Date;











/**
 *
 * @author Jazmine
 */
public class Clientes {
    private int cedula;
    private String nombreC;
    private Date Fnacimiento;
    private int telefono;
    private String correo;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public Date getFnacimiento() {
        return Fnacimiento;
    }

    public void setFnacimiento(Date Fnacimiento) {
        this.Fnacimiento = Fnacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Clientes(int cedula, String nombreC, Date Fnacimiento, int telefono, String correo) {
        this.cedula = cedula;
        this.nombreC = nombreC;
        this.Fnacimiento = Fnacimiento;
        this.telefono = telefono;
        this.correo = correo;
    }
    
}
