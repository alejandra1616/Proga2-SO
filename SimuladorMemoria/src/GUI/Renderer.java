/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author reese
 * 
 * Esta clase se encarga de cambiarle el color a las celdas
 */
public class Renderer extends DefaultTableCellRenderer{

    HashMap <Object, Color> coloresDic = new HashMap<>();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(column == 0){
            if( coloresDic.containsKey(value) ){
                System.out.println(value);
                setBackground(coloresDic.get(value));
            }
            else{
                Color c = crearColor();
                coloresDic.put(value,c);
                setBackground(c);
            }
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Color crearColor(){
        Random rand = new Random();
       
        float r = (float) (rand.nextFloat() / 2f + 0.5);
        float g = (float) (rand.nextFloat() / 2f + 0.5);
        float b = (float) (rand.nextFloat() / 2f + 0.5);
        
        Color randomColor = new Color(r, g, b);
        
        return randomColor;
   }
    
}
