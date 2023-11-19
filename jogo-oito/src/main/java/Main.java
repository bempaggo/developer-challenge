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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Integer> estadoInicial = new ArrayList<>();
        ArrayList<Integer> estadoFinal = new ArrayList<>();


        estadoInicial.add(1);
        estadoInicial.add(2);
        estadoInicial.add(3);
        estadoInicial.add(5);
        estadoInicial.add(0);
        estadoInicial.add(6);
        estadoInicial.add(4);
        estadoInicial.add(7);
        estadoInicial.add(8);

        
        estadoFinal.add(1);
        estadoFinal.add(2);
        estadoFinal.add(3);
        estadoFinal.add(4);
        estadoFinal.add(5);
        estadoFinal.add(6);
        estadoFinal.add(7);
        estadoFinal.add(8);
        estadoFinal.add(0);
        
        State si = new State(estadoInicial);
        State sf = new State(estadoFinal);
        
        Scanner scanner;
        Scanner scanner2;
        int option;
        int qtdMoves;
        
        do{  
            System.out.println("Estado inicial:");
            si.printState();
            System.out.println();

            System.out.println("Solucao a ser procurada:");
            sf.printState();
            System.out.println();

            System.out.println("Digite:");
            System.out.println("1 - Resolver por busca em largura (BFS)");
            System.out.println("2 - Resolver por busca A*");
            System.out.println("3 - Randomizar tabuleiro");
        
            scanner = new Scanner(System.in);
                       
            option = scanner.nextInt();
            
            switch (option) {
                case 1 -> {
                    BFS bfs = new BFS(si.n,sf.n);
                    bfs.solve();
                }
                case 2 -> {
                    AStar astar = new AStar(si.n,sf.n);
                    astar.solve();
                }
                
                case 3 -> {
                    System.out.println("Quantos movimentos aleatorios deseja realizar?");
                    scanner2 = new Scanner(System.in);
                    qtdMoves = scanner2.nextInt();
                    si.randomize(qtdMoves);
                }
                
                default -> System.out.println("Digite uma opção va3lida");
            }
        }while (!(option==1 || option==2));
        
       
    }
    
}
