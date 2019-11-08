/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladormemoria;

import static java.lang.Math.abs;
import static simuladormemoria.Simulacion.tamañoPagina;

/**
 *
 * @author Leo
 */
public class Requi {
    
    private String proceso;
    private int direccion;
    private int numeroPagina = abs(direccion/25);

    public Requi(String proceso, int direccion) {
        this.proceso = proceso;
        this.direccion = direccion;
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
       
}
