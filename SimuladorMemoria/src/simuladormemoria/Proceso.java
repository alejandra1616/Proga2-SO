package simuladormemoria;

import java.awt.Color;

/**
 *
 * @author emers
 */
public class Proceso 
{
    
    private String id;
    private int requerimiento; //preguntar a qué se refiere.
    private int prioridad;

    public Proceso(String id, int requerimiento, int prioridad) {
        this.id = id;
        this.requerimiento = requerimiento;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(int requerimiento) {
        this.requerimiento = requerimiento;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
  
    
}
