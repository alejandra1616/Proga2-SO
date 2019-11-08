package simuladormemoria;

/**
 *
 * @author Ale
 */
public class Pagina {
    private int id;
    private Proceso proceso;
    private boolean sucia;
    private int cantAcceso;
    
    
    public Pagina(int id,Proceso proceso) {
        this.id= id;
        this.proceso=proceso;
        this.sucia=false;
        this.cantAcceso=0;

    }
    
    public Pagina(Proceso proceso,boolean sucia,int cantAcceso) {
        this.id= id;
        this.proceso=proceso;
        this.sucia=sucia;
        this.cantAcceso=cantAcceso;

    }


    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public boolean isSucia() {
        return sucia;
    }

    public void setSucia(boolean sucia) {
        this.sucia = sucia;
    }

    public int getCantAcceso() {
        return cantAcceso;
    }

    public void setCantAcceso(int cantAcceso) {
        this.cantAcceso = cantAcceso;
    }
    
}
