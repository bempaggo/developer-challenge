/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.gpt;

/**
 *
 * @author diego
 */
public class Peca {
    private int numX;
    private int numY;
    private int number;
    
    public Peca(int number, int x, int y) {
        this.number = number;
        this.numX = x;
        this.numY = y;
    }
    
    public void setNumX(int value){
        this.numX = value;
    }
    
    public int getNumX(){
        return this.numX;
    }
    
    public void setNumY(int value){
        this.numY = value;
    }
    
    public int getNumY(){
        return this.numY;
    }
    
    public void setNumber(int value){
        this.number = value;
    }
    
    public int getNumber(){
        return this.number;
    }
}
