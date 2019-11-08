package simuladormemoria;

import java.sql.Timestamp;

        
public class Pagina {
    
    private int id;
    private Proceso proceso;
    private boolean sucia;
    private int cantAcceso;
    private Timestamp horaEntrada;
    private Timestamp horaUso;
    private int contadorReferencia;
     
    public Pagina(int id, Proceso proceso,boolean sucia,int cantAcceso) 
    {
        this.id= id;
        this.proceso=proceso;
        this.sucia=sucia;
        this.cantAcceso=cantAcceso;

    }

    public int getContadorReferencia() {
        return contadorReferencia;
    }

    public void setContadorReferencia(int contadorReferencia) {
        this.contadorReferencia = contadorReferencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraUso() {
        return horaUso;
    }

    public void setHoraUso(Timestamp horaUso) {
        this.horaUso = horaUso;
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
