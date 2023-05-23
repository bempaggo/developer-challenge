/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.gpt;

import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author diego
 */
public class Tabuleiro {
    
    private int[][] tabuleiro;
    
    public Tabuleiro() {
        tabuleiro = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        this.embaralhar();
    }
    
    public int getValue(int x, int y){
        return this.tabuleiro[x][y];
    }
    
    public int[][] getTabuleiro(){
        return this.tabuleiro;
    }
    
    public void setValue(int x, int y, int value) {
        this.tabuleiro[x][y] = value;
    }
    
    public void setTabuleiro( int[][] value){
        this.tabuleiro = value;
    }
    
    public void embaralhar() {
        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        tabuleiro = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        
        //imprime sequência de 10 números inteiros aleatórios entre 0 e 25
        int count = 0;
        int x = 0,y = 0, z = 0;
        while (count < 8) {
            int valor = gerador.nextInt(9);
            int result = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(tabuleiro[i][j] == valor)
                        result = 1;
                    if(tabuleiro[i][j] == 0 && x == 0 && y == 0 && z == 0){
                        x = i;
                        y = j;
                        z = 1;
                    }
                }
            }
            if (result == 0) {
                tabuleiro[x][y] = valor;
                count++;
            }
            x = 0;
            y = 0;
            z = 0;
        }
    }
    
    public void ordenar() {
        tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    }
}
