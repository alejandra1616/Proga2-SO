package simuladormemoria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Simulacion 
{
    
    public static LinkedList <String> procesosTXT;
    public static LinkedList <Pagina> memoriaVirtual = new LinkedList<>();
    
    public Simulacion()
    {
        procesosTXT = new LinkedList<>();
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
    
    public static LinkedList<Proceso> TXTProcesos()
    {
        String nombre;
        int requerimiento = 0;
        int prioridad = 0;
        String [] lista;
        LinkedList <Proceso> TXT = new LinkedList<>();
        
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
    
    public static void impresionListas(LinkedList<Proceso> resultado)
    {
        for(int i=0; i< resultado.size(); i++)
        {
            System.out.println(resultado.get(i).getId());
            System.out.println();
        }
           
    }
    
    public static void impresionListass()
    {
        for(int i=0; i< memoriaVirtual.size(); i++)
        {
            System.out.println(memoriaVirtual.get(i).getProceso().getId());
            System.out.println();
        }
           
    }
    
    public static LinkedList <Proceso> ordenaProcesos()
    {
        
        LinkedList <Proceso> CurrentList = new LinkedList<>();
        LinkedList <Proceso> listaProcesos = TXTProcesos();
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
        
        LinkedList <Proceso> procesosOrdenados = ordenaProcesos();
        int cantidadPags = 0;
    
        for(int i=0; i < procesosOrdenados.size(); i++)
        {
            if(multiprogramacion!=0)
            {
                cantidadPags = procesosOrdenados.get(i).getRequerimiento() / pagSize;
                
                while(cantidadPags!=0)
                {
                    Pagina pag = new Pagina(procesosOrdenados.get(i), false, 0);
                    memoriaVirtual.add(pag);
                    cantidadPags--;
                }
                
                multiprogramacion--;
            
            }else{break;}
        }    

        
    }
    
    
    public static void main (String [ ] args) 
    {
        Simulacion sim = new Simulacion();
        creaPagina(50, 3);
        impresionListass();
        
    }
    
    
    
    
    
    
}
