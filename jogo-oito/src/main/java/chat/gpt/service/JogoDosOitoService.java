package chat.gpt.service;

import chat.gpt.model.JogoDosOitoModel;

import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JogoDosOitoService {

    private JogoDosOitoModel jogo;

    public JogoDosOitoService(JogoDosOitoModel jogo) {
        this.jogo = jogo;
    }

    public int[][] getTabuleiro() {
        return jogo.getTabuleiro();
    }

    public JButton[][] getBotoes() {
        return jogo.getBotoes();
    }

    public void setBotaoReiniciar(JButton botaoReiniciar) {
        jogo.setBotaoReiniciar(botaoReiniciar);
    }

    public void gerarTabuleiro() {
        Random random = new Random();
        Set<Integer> generatedNumbers = new HashSet<>();

        // preenche a matriz com valores aleatórios sem repetições
        for (int i = 0; i < jogo.getTabuleiro().length; i++) {
            for (int j = 0; j < jogo.getTabuleiro()[i].length; j++) {
                int randomNumber;
                do {
                    randomNumber = random.nextInt(9); // gera um número aleatório entre 0 e 8
                } while (generatedNumbers.contains(randomNumber));
                jogo.getTabuleiro()[i][j] = randomNumber;
                generatedNumbers.add(randomNumber);
            }
        }
        generatedNumbers.clear();
    }

    public void atualizarTabuleiro() {
        for (int i = 0; i < jogo.getTabuleiro().length; i++) {
            for (int j = 0; j < jogo.getTabuleiro()[i].length; j++) {
                JButton botao = getBotoes()[i][j];
                int valor = getTabuleiro()[i][j];
                if (valor == 0) {
                    botao.setText("");
                } else {
                    botao.setText(String.valueOf(valor));
                }
            }
        }
    }

    public boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < jogo.getTabuleiro().length; i++) {
            for (int j = 0; j < jogo.getTabuleiro()[i].length; j++) {
                if (getTabuleiro()[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public void reiniciarJogo() {
        gerarTabuleiro();
        atualizarTabuleiro();
    }



    public void mover(int indexLinha, int indexColuna) {
        int[][] tabuleiro = this.getTabuleiro();
        int tamanhoTabuleiro = tabuleiro.length;

        if (indexLinha + 1 < tamanhoTabuleiro && tabuleiro[indexLinha + 1][indexColuna] == 0) {
            JButton botaoAtual = getBotoes()[indexLinha][indexColuna];
            JButton botaoSucessor = getBotoes()[indexLinha + 1][indexColuna];
            int valorBotaoAtual = getTabuleiro()[indexLinha][indexColuna];
            getTabuleiro()[indexLinha + 1][indexColuna] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            getTabuleiro()[indexLinha][indexColuna] = 0;
            botaoAtual.setText("");
        } else if (indexLinha - 1 >= 0 && tabuleiro[indexLinha - 1][indexColuna] == 0) {
            JButton botaoAtual = getBotoes()[indexLinha][indexColuna];
            JButton botaoSucessor = getBotoes()[indexLinha - 1][indexColuna];
            int valorBotaoAtual = getTabuleiro()[indexLinha][indexColuna];
            getTabuleiro()[indexLinha - 1][indexColuna] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            getTabuleiro()[indexLinha][indexColuna] = 0;
            botaoAtual.setText("");
        } else if (indexColuna + 1 < tamanhoTabuleiro && tabuleiro[indexLinha][indexColuna + 1] == 0) {
            JButton botaoAtual = getBotoes()[indexLinha][indexColuna];
            JButton botaoSucessor = getBotoes()[indexLinha][indexColuna + 1];
            int valorBotaoAtual = getTabuleiro()[indexLinha][indexColuna];
            getTabuleiro()[indexLinha][indexColuna + 1] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            getTabuleiro()[indexLinha][indexColuna] = 0;
            botaoAtual.setText("");
        } else if (indexColuna - 1 >= 0 && tabuleiro[indexLinha][indexColuna - 1] == 0) {
            JButton botaoAtual = getBotoes()[indexLinha][indexColuna];
            JButton botaoSucessor = getBotoes()[indexLinha][indexColuna - 1];
            int valorBotaoAtual = getTabuleiro()[indexLinha][indexColuna];
            getTabuleiro()[indexLinha][indexColuna - 1] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            getTabuleiro()[indexLinha][indexColuna] = 0;
            botaoAtual.setText("");
        }


        if (jogoConcluido()) {
            JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
            reiniciarJogo();
        }
    }
}