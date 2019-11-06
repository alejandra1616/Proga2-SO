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
    
    
}
