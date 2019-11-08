/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladormemoria;

import static GUI.Central1.tamañoPagina;
import static java.lang.Math.abs;

/**
 *
 * @author Leo
 */
public class Requi {
    
    private String proceso;
    private int direccion;
    private int numeroPagina;

    public Requi(String proceso, int direccion) {
        this.proceso = proceso;
        this.direccion = direccion;
        numeroPagina = abs(this.direccion/tamañoPagina);
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }      
}
