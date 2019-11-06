package simuladormemoria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Simulacion 
{
    
    public static LinkedList <String> procesosTXT;
    
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
    
    
    public static void main (String [ ] args) 
    {
        Simulacion sim = new Simulacion();
        impresionListas(TXTProcesos());
        
    }
    
    
    
    
}
