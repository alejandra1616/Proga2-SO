/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladormemoria;

import GUI.Central1;
import static simuladormemoria.Simulacion.lista_requis;

/**
 *
 * @author Ale
 */
public class SimuladorMemoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Central1 c = new Central1();
        //c.show();
        System.out.println("Ale se la come");
        Requi r1 = new Requi("Proceso1", 5);
        Requi r2 = new Requi("Proceso2", 450);
        Requi r3 = new Requi("Proceso3", 200);
        Requi r4 = new Requi("Proceso1", 100);
        lista_requis.add(r1);
        lista_requis.add(r2);
        lista_requis.add(r3);
        lista_requis.add(r4);
        
        
    }
    

    
}
