
package oficiales;

import java.util.Date;

/**
 *
 * @author Jazmine
 */
public class Oficiales {
    private int Ncedula;
    private String nombreC;
    private Date Fnacimiento;
    private int teléfono;
    private String  Celectrónico;
    private int salario;
    private String tipo;

    public int getNcedula() {
        return Ncedula;
    }

    public void setNcedula(int Ncedula) {
        this.Ncedula = Ncedula;
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

    public int getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(int teléfono) {
        this.teléfono = teléfono;
    }

    public String getCelectrónico() {
        return Celectrónico;
    }

    public void setCelectrónico(String Celectrónico) {
        this.Celectrónico = Celectrónico;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Oficiales(int Ncedula, String nombreC, Date Fnacimiento, int teléfono, String Celectrónico, int salario, String tipo) {
        this.Ncedula = Ncedula;
        this.nombreC = nombreC;
        this.Fnacimiento = Fnacimiento;
        this.teléfono = teléfono;
        this.Celectrónico = Celectrónico;
        this.salario = salario;
        this.tipo = tipo;
    }

    public Oficiales(int Ncedula) {
        this.Ncedula = Ncedula;
    }
     
    @Override
    public String toString() {
        return "oficiales{" + "Ncedula=" + Ncedula + ", nombreC=" + nombreC + ", Fnacimiento=" + Fnacimiento + ", tel\u00e9fono=" + teléfono + ", Celectr\u00f3nico=" + Celectrónico + ", salario=" + salario + '}';
    }
    
}
