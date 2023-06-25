package chat.gpt.model;

import javax.swing.*;

import chat.gpt.view.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameModel {
    GameView view;

    private int[][] tabuleiro;

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public GameModel(GameView view) {
        this.view = view;
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i < 9; i++) { // Começa pelo 1 e exclui o 0
            numeros.add(i);
        }
        Collections.shuffle(numeros); // Embaralhando os elementos
        numeros.add(0); // Adiciona o 0 ao final

        // Preenche o tabuleiro
        this.tabuleiro = new int[][]{{numeros.get(0), numeros.get(1), numeros.get(2)},
                {numeros.get(3), numeros.get(4), numeros.get(5)},
                {numeros.get(6), numeros.get(7), numeros.get(8)}};
    }

    // Metodo responsável por realizar as jogadas
    public void mover(int linha, int coluna) {
        int linhaVazia = -1;
        int colunaVazia = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }

        int novaLinha = linhaVazia + linha;
        int novaColuna = colunaVazia + coluna;
        if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
            // movimento inválido
            return;
        }

        // Verifica se o número está adjacente ao quadrado vazio
        int linhaMovida = linhaVazia + linha;
        int colunaMovida = colunaVazia + coluna;

        if (linhaMovida != linhaVazia && colunaMovida != colunaVazia) {
            // movimento inválido (em diagonal)
            return;
        }

        // Realiza a troca de duas peças no tabuleiro
        tabuleiro[linhaVazia][colunaVazia] = tabuleiro[novaLinha][novaColuna];
        // Atribui o valor 0 à posição da peça que foi movida
        tabuleiro[novaLinha][novaColuna] = 0;

        // Atualiza a vizualização do tabuleiro
        view.atualizarTabuleiro(tabuleiro);

        // Depois de verificado se as peças estão ordenadas o if abaixo é chamado
        if (jogoConcluidoComSucesso()) {
            JOptionPane.showMessageDialog(this.view, "Parabéns, você venceu!");
            reiniciar();
        }
    }

    // Verificando se foi concluido
    private boolean jogoConcluidoComSucesso() {

        int count = 1;
        // Percorre o tabuleiro do jogo e compara cada elemento com uma sequencia de 1 a 9
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // % 9 garante que o valor de count sempre sera um numero entre 1 e 9
                if (tabuleiro[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    // Reinicia o jogo embaralhando as peças
    public void reiniciar() {
        List<Integer> numeros = new ArrayList<>();

        // insere  os numeros de 1 a 8 a lista numeros criada acima
        for (int i = 1; i < 9; i++) {
            numeros.add(i);
        }

        Collections.shuffle(numeros); // Embaralha as peças
        numeros.add(0); // Adiciona 0 ao final

        // Atualiza a matriz com as novas informações
        this.tabuleiro = new int[][]{
                {numeros.get(0), numeros.get(1), numeros.get(2)},
                {numeros.get(3), numeros.get(4), numeros.get(5)},
                {numeros.get(6), numeros.get(7), numeros.get(8)}
        };
    }

    // os dois metodos abaixo sao responsaveis por envontrar as posições vazias
    public int getLinhaVazia() {
        // Percorre o tabuleiro para encontrar a posição onde está o 0
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    return i;
                }
            }
        }
        // Caso não houver posição vazia no tabuleiro retornara -1 para indicar a ausencia
        return -1;
    }

    public int getColunaVazia() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1;
    }
}
