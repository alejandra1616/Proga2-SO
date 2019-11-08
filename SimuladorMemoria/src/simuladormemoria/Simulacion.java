package simuladormemoria;

import static GUI.Central1.tamañoMemoriaFisica;
import static GUI.Central1.tamañoPagina;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Simulacion 
{
    
    public static ArrayList <String> procesosTXT;
    public static ArrayList <String> requisicionesTXT;
    public static ArrayList<Requi> lista_requis = new ArrayList<Requi>();;
    public static ArrayList <ArrayList <Pagina>> memoriaVirtual = new ArrayList<ArrayList <Pagina>>();
    public static ArrayList <Pagina> memoriaFisica = new ArrayList<>(abs(tamañoMemoriaFisica/tamañoPagina));
    
    public Simulacion(){
        procesosTXT = new ArrayList<>();
        //File f = new File( "C:\\Users\\emers\\Desktop\\Procesos.txt" );
        File f = new File("C:\\Users\\Leo\\Desktop\\Procesos.txt");
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader( new FileReader( f ) );
            String linea;
            while(entrada.ready())
            {
                linea = entrada.readLine();
                procesosTXT.add(linea);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
        try{
            entrada.close();
        }catch(IOException e1){}
        }
        
    }
    
    public static ArrayList<Proceso> TXTProcesos(){
        String nombre;
        int requerimiento = 0;
        int prioridad = 0;
        String [] lista;
        ArrayList <Proceso> TXT = new ArrayList<>();
        
        for(int i = 0; i < procesosTXT.size(); i++)
        {
            lista = procesosTXT.get(i).split(" ", 3);
            nombre = lista[0];
            requerimiento = Integer.parseInt(lista[1]);
            prioridad =  Integer.parseInt(lista[2]);
                    
            Proceso pro = new Proceso(nombre, requerimiento, prioridad);
            TXT.add(pro);
            
        }

        return TXT;
        
    }    
    
    public static void impresionListas(ArrayList<Proceso> resultado){
        for(int i=0; i< resultado.size(); i++)
        {
            System.out.println(resultado.get(i).getId());
            System.out.println();
        }
           
    }
    
    public static void impresionListasVirtual(){
        System.out.println("NUM PROCESOS en virtual: " + memoriaVirtual.size());
        
        for(int i=0; i< memoriaVirtual.size(); i++)
        {
            for(int j=0; j < memoriaVirtual.get(i).size(); j++)
            {
                
                System.out.println(memoriaVirtual.get(i).get(j).getProceso().getId());
                System.out.println();

            }
        }
           
    }
    
    public static void impresionListaFisica(){
        System.out.println("NUM PROCESOS en fisica: " + memoriaFisica.size());
        
        for(int i=0; i< memoriaFisica.size(); i++)
        {
            System.out.println(memoriaFisica.get(i).getId());
            System.out.println(memoriaFisica.get(i).getProceso().getId());
        }
           
    }
    
    public static void impresionListaRequi(){
        System.out.println("NUM de requis: " + lista_requis.size());
        
        for(int i=0; i< lista_requis.size(); i++)
        {
            System.out.println(lista_requis.get(i).getProceso());
            
        }
           
    }
    
    public static ArrayList <Proceso> ordenaProcesos(){
        
        ArrayList <Proceso> CurrentList = new ArrayList<>();
        ArrayList <Proceso> listaProcesos = TXTProcesos();
        boolean flag = false;
        int num = 0;
        for(int i=0; i < listaProcesos.size(); i++)
        {
            flag = false;

            if(CurrentList.isEmpty())
            {
               CurrentList.add(listaProcesos.get(i));
            }else
            {

                for(int j=0; j < CurrentList.size(); j++)
                {
                    if(listaProcesos.get(i).getPrioridad() < CurrentList.get(j).getPrioridad() && flag==false)
                    {
                        CurrentList.add(j, listaProcesos.get(i));
                        flag = true;
                    }
                }
                
                if(flag==false)
                {
                    CurrentList.add(listaProcesos.get(i));
                }
                
            }
        }
        
        return CurrentList;

    }
    
    public static void creaPagina(int pagSize, int multiprogramacion){
        
        ArrayList <Proceso> procesosOrdenados = ordenaProcesos();
        int cantidadPags = 0;
        int contador= 0;
    
        for(int i=0; i < procesosOrdenados.size(); i++){
            ArrayList <Pagina> paginasTemporal = new ArrayList<>();
            contador = 0;
            if(multiprogramacion!=0)
            {
                cantidadPags = procesosOrdenados.get(i).getRequerimiento() / pagSize;
                
                while(cantidadPags!=0)
                {
                    Pagina pag = new Pagina(contador, procesosOrdenados.get(i), false, 0);
                    paginasTemporal.add(pag);
                    cantidadPags--;
                    contador++;
                }
                
                multiprogramacion--;
            
            }else{break;}
            
            
            memoriaVirtual.add(paginasTemporal);
        }    

        
    }
    
    public static void Prepaging(int cantFrames){
        for(int i=0; i< memoriaVirtual.size(); i++)
        {
           ArrayList<Pagina> list=memoriaVirtual.get(i);
           for(int j=0; j<cantFrames;j++){
               //System.out.println(list.get(j));
               Timestamp currentTime = new Timestamp(System.currentTimeMillis());
               list.get(j).setHoraEntrada(currentTime);
               list.get(j).setHoraUso(currentTime);
               list.get(j).aumentarAcceso();
               memoriaFisica.add((Pagina) list.get(j));
           }  
        }
        System.out.println(memoriaFisica);
        
    }
    
    //------------------------ Replacement policy -------------------------------

    public static Pagina LFU(ArrayList<Pagina> lista){
        Pagina menorPagina = lista.get(0);
        for(int i=0;i<lista.size();i++){
            if(menorPagina.getCantAcceso() > lista.get(i).getCantAcceso()){
                menorPagina = lista.get(i);
                
            }
        }
        
        //System.out.println(menorPagina.getCantAcceso());
        return menorPagina;
    }  
    
    public static Pagina FIFO(ArrayList<Pagina> lista){
        
        System.out.println("LISTA DE FIFO TIENE: "  +  lista.size());
        Pagina paginaTemporal = lista.get(0);
        //System.out.println(lista.get(0).getId());
        for(int i=0; i < lista.size(); i++){
            //System.out.println(lista.get(i).getHoraEntrada());
            if(paginaTemporal.getHoraEntrada().after(lista.get(i).getHoraEntrada())){
                paginaTemporal = lista.get(i);
            }
        }       
        return paginaTemporal;
       
    }
    
    public static Pagina LRU(ArrayList<Pagina> lista){
        System.out.println("LISTA DE LRU TIENE: "  +  lista.size());
        Pagina paginaTemporal = lista.get(0);
        for(int i=0; i < lista.size(); i++){
            if(paginaTemporal.getHoraUso().after(lista.get(i).getHoraUso())){
                paginaTemporal = lista.get(i);
            }
        }
       
        return paginaTemporal;
        
    }
    
    public static Pagina MRU(ArrayList<Pagina> lista){
        Pagina paginaTemporal = lista.get(0);
        for(int i=0; i < lista.size(); i++){
            if(paginaTemporal.getHoraUso().before(lista.get(i).getHoraUso())){
                paginaTemporal = lista.get(i);
            }
        }
        
        return paginaTemporal;
        
    }
    
    public static Pagina SecondChance (ArrayList<Pagina> lista){
        Pagina paginaTemporal = lista.get(0);
        //System.out.println(lista.get(0).getId());
        for(int i=0; i < lista.size(); i++){
            //System.out.println(lista.get(i).getHoraEntrada());
            if(paginaTemporal.getHoraEntrada().after(lista.get(i).getHoraEntrada()) && paginaTemporal.isSucia()== false){
                paginaTemporal = lista.get(i);
            }
        }

        //System.out.println("--------------------------------------");
        //System.out.println(paginaTemporal.getHoraEntrada());
        //System.out.println(paginaTemporal.getId());
        
        return paginaTemporal;
       
    }
    
    public static void cargaRequisiciones(){
        requisicionesTXT = new ArrayList<>();
        File f = new File( "C:\\Users\\Leo\\Desktop\\Requisiciones.txt" );
        //File f = new File("C:\\Users\\emers\\Desktop\\Requisiciones.txt");
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader( new FileReader( f ) );
            String linea;
            while(entrada.ready())
            {
                linea = entrada.readLine();
                requisicionesTXT.add(linea);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
        try{
            entrada.close();
        }catch(IOException e1){}
        }
        
        
        System.out.println("Requs: " + requisicionesTXT);
        
    }
    
    public static void TXTRequisiciones(){
        
        cargaRequisiciones();
        
        String nombre;
        int direccion = 0;
        String [] lista;
             
        for(int i = 0; i < requisicionesTXT.size(); i++)
        {
            lista = requisicionesTXT.get(i).split(" ", 2);
            nombre = lista[0];
            direccion = Integer.parseInt(lista[1]);
                    
            Requi re = new Requi(nombre, direccion);
            lista_requis.add(re);
            
        }
       
    }   
    
    public static ArrayList <Pagina> local(Requi Requisito){
        ArrayList <Pagina> subLista = new ArrayList<>();
        System.out.println("memoria fisica: "  +  memoriaFisica.size());
        for (int i=0; i < memoriaFisica.size(); i++)
        {
            System.out.println("Proceso de reque " + Requisito.getProceso());
            System.out.println("Prceso memoria fisica "+ memoriaFisica.get(i).getProceso().getId() );
            if(memoriaFisica.get(i).getProceso().getId().equals(Requisito.getProceso()))
            {
                subLista.add(memoriaFisica.get(i));
                System.out.println("Se agrega a la sublista");
            }
            
        }
        System.out.println("LISTA DE LOCAL TIENE: "  +  subLista.size());
        return subLista;       
    }
    
    public static void cambioPagina(Pagina pagEntra, Pagina pagSale){
        System.out.println("Proceso Entra " +pagSale.getProceso().getId());
        System.out.println("Proceso Sale " + pagEntra.getProceso().getId());
        for(int i=0; i < memoriaFisica.size(); i++)
        {
            //System.out.println("qqqqqq " + pagSale.getProceso().getId());
            //System.out.println("pppppp " + memoriaFisica.get(i).getProceso().getId());
            if(pagSale.getId()==memoriaFisica.get(i).getId()
                    && pagSale.getProceso().getId().equals(memoriaFisica.get(i).getProceso().getId()))
            {
                System.out.println("MMMMM " + memoriaFisica.get(i).getProceso().getId());
                memoriaFisica.remove(i);
                pagEntra.setSucia(true);
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                pagEntra.setHoraEntrada(currentTime);
                pagEntra.setHoraUso(currentTime);
                pagEntra.aumentarAcceso();
                
                memoriaFisica.add(i, pagEntra);
                break;
            }
        }
        Simulacion.impresionListaFisica();
    }
    
    public static Pagina traerDeMemoriaVirtual(Requi request){
        System.out.println("Entra traer memoria");
        int num=request.getNumeroPagina();
        Pagina respuesta = new Pagina();
        for(int i=0; i< memoriaVirtual.size(); i++){
           if(request.getProceso().equals(memoriaVirtual.get(i).get(0).getProceso().getId())){
               respuesta= memoriaVirtual.get(i).get(num);
               break;
           }
        }      
        return respuesta;
    }
    
    public static void cleaning(){
        
        
    }
    
    /*
    public static void main (String [ ] args) 
    {
        Simulacion sim = new Simulacion();
        creaPagina(25, 3);
        //impresionListasVirtual();
        //Son tres frames para prepaging porque empieza en 0.
        Prepaging(3);
        //impresionListaFisica();
        TXTRequisiciones();
        //impresionListaRequi();
        
        
    }*/
    
    
    
    
    
    
}
