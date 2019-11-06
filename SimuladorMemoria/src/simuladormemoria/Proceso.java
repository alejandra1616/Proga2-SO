package simuladormemoria;

/**
 *
 * @author emers
 */
public class Proceso 
{
    
    private int id;
    private int requerimiento; //preguntar a qué se refiere.
    private int prioridad;

    public Proceso(int id, int requerimiento, int prioridad) {
        this.id = id;
        this.requerimiento = requerimiento;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
