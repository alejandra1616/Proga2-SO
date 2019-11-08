package simuladormemoria;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Simulacion 
{
    
    public static ArrayList <String> procesosTXT;
    public static ArrayList <ArrayList <Pagina>> memoriaVirtual = new ArrayList<ArrayList <Pagina>>();
    public static ArrayList <Pagina> memoriaFisica = new ArrayList<>(80);
    
    public Simulacion()
    {
        procesosTXT = new ArrayList<>();
        File f = new File( "C:\\Users\\emers\\Desktop\\Procesos.txt" );
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
    
    public static ArrayList<Proceso> TXTProcesos()
    {
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
    
    public static void impresionListas(ArrayList<Proceso> resultado)
    {
        for(int i=0; i< resultado.size(); i++)
        {
            System.out.println(resultado.get(i).getId());
            System.out.println();
        }
           
    }
    
    public static void impresionListasVirtual()
    {
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
    
        public static void impresionListaFisica()
    {
        System.out.println("NUM PROCESOS en fisica: " + memoriaFisica.size());
        
        for(int i=0; i< memoriaFisica.size(); i++)
        {
            System.out.println(memoriaFisica.get(i).getId());
            System.out.println(memoriaFisica.get(i).getProceso().getId());
        }
           
    }
    
    public static ArrayList <Proceso> ordenaProcesos()
    {
        
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
    
    public static void creaPagina(int pagSize, int multiprogramacion)
    {
        
        ArrayList <Proceso> procesosOrdenados = ordenaProcesos();
        int cantidadPags = 0;
        int contador= 0;
    
        for(int i=0; i < procesosOrdenados.size(); i++)
        {
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
               memoriaFisica.add((Pagina) list.get(j));
           }  
        }
        System.out.println(memoriaFisica);
        
    }
    
    
    public static void main (String [ ] args) 
    {
        Simulacion sim = new Simulacion();
        creaPagina(25, 3);
        impresionListasVirtual();
        //Son tres frames para prepaging porque empieza en 0.
        Prepaging(3);
        impresionListaFisica();
        
    }
    
    
    
    
    
    
}
