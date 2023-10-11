/*
 * Trabalho Jogo dos 8 e Metodos de busca
 * 
 * Inteligencia Artificial - GCC128
 * 
 * Sistemas de Informação
 * Universidade Federal de Lavras
 * 
 * 
 * Autor: Alfredo
 * 
 */

import java.util.ArrayList;
import java.util.Objects;

public class State implements Comparable<State>{
    
    public ArrayList<Integer> n;
    public int cost;
         
    
    //construtor para BFS
    public State (ArrayList v){
        n = new ArrayList<>(v);
    }
    
    //construtor para AStar
    public State(ArrayList stateIni, ArrayList stateFin){
        n = new ArrayList<>(stateIni);
        this.cost = calculateCost(stateFin);     //já calcula o custo do estado com relação ao final
    }
    
        
    //calcula o custo em relação ao estado final com base em uma tabela de custos pre-calculada
    public final int calculateCost(ArrayList<Integer> stateFinal){
        int totalCost = 0;
        int auxFinalIndex;
        int[][] costTable = {
                {0,1,2,1,2,3,2,3,4},
                {1,0,1,2,1,2,3,2,3},
                {2,1,0,3,2,1,4,3,2},
                {1,2,3,0,1,2,1,2,3},
                {2,1,2,1,0,1,2,1,2},
                {3,2,1,2,1,0,3,2,1},
                {2,3,4,1,2,3,0,1,2},
                {3,2,3,2,1,2,1,0,1},
                {4,3,2,3,2,1,2,1,0}
        };
        for(int i=0; i<this.n.size();i++){
            auxFinalIndex = stateFinal.indexOf(this.n.get(i));
            totalCost = totalCost + costTable[i][auxFinalIndex];
        }
        return totalCost;
    }
    
    
    public int getCost(){
        return cost;
    }
    
    
    
    //imprime o estado em 3x3
    public void printState(){
        for (int i=0; i<n.size();i++){
            System.out.print(n.get(i)+ "  ");
            if (i%3==2){
                System.out.println();
            }
        }
    }
    
    
    //Encontra em qual posição está o Zero
    public int findZero(){
        return n.indexOf(0);
    }
    
    ////move a casa que contem zero para CIMA se for possível, caso contrario retorna null
    public State moveUp(){
        State s = new State(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos-3));
            s.n.set(zeroPos-3, 0);
        }
        return s;
    }
    
    ////move a casa que contem zero para BAIXO se for possível, caso contrario retorna null
    public State moveDown(){
        State s = new State(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos+3));
            s.n.set(zeroPos+3, 0);
        }
        return s;
    }
    
    //move a casa que contem zero para a ESQUERDA se for possível, caso contrario retorna null
    public State moveLeft(){
        State s = new State(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos-1));
            s.n.set(zeroPos-1, 0);
        }
        return s;
    }
    
    //move a casa que contem zero para a DIREITA se for possível, caso contrario retorna null
    public State moveRight(){
        State s = new State(this.n);
        int zeroPos = s.findZero();
        if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
            s = null;
        }else{
            s.n.set(zeroPos, s.n.get(zeroPos+1));
            s.n.set(zeroPos+1, 0);
        }
        return s;
    }
    
    //metodo para gerar possíveis filhos e retornar lista de filhos
    public ArrayList<State> genChildren(){
        ArrayList<State> children = new ArrayList<>();
        State s;
        s = this.moveUp();
        if (s != null){
            children.add(s);
        }
        s = this.moveDown();
        if (s != null){
            children.add(s);
        }
        s = this.moveLeft();
        if (s != null){
            children.add(s);
        }
        s = this.moveRight();
        if (s != null){
            children.add(s);
        }
        return children;
    }
    
    //executa n movimentos para embaralhar o tabuleiro
    public void randomize(int n){
        int zeroPos;
        int rand;
        
        for (int i=0; i<n;i++){
            zeroPos = this.findZero();
            rand = (int)Math.floor(Math.random()*(4-1+1)+1);
            switch(rand){
                case 1 -> { //move up
                    if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos-3));
                        this.n.set(zeroPos-3, 0);
                    }
                }
                case 2 -> { //move down
                    if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+3));
                        this.n.set(zeroPos+3, 0);
                    }
                }
                case 3 -> { //move left
                    if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
                        
                    }else{ 
                        this.n.set(zeroPos, this.n.get(zeroPos-1));
                        this.n.set(zeroPos-1, 0);
                    }
                }
                
                case 4 -> {//move right
                    if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+1));
                        this.n.set(zeroPos+1, 0);
                    }
                }
            }
        }
    }
    
    /**
     * compara o objeto com outro recebido
     * 
     * @param o => recebe como parametro outro objeto tipo State para comparação de seus ArrayList
     * @return retorna true caso os dois Objetos tenham seus ArrayList com os mesmos valores
     */
    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (o.getClass()!= this.getClass()){
            return false;
        }
        boolean result = false;
        State s = (State) o;
        for (int i=0; i<n.size(); i++){
            if (this.n.get(i).equals(s.n.get(i)) ){
                result = true;
            }else {
                return false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.n);
        return hash;
    }
    
    //Utilizado para a PriorityQueue
    @Override
    public int compareTo(State s){
        return (this.getCost()-s.getCost());
    }

}
