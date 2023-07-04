package chat.gpt.service;

import chat.gpt.model.JogoModel;

import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JogoService {

    private JogoModel jogo;

    public JogoService(JogoModel jogo) {
        this.jogo = jogo;
    }

    public void gerarTabuleiro() {
        Random random = new Random();
        Set<Integer> generatedNumbers = new HashSet<>();

        // preenche a matriz com valores aleatórios sem repetições
        for (int i = 0; i < jogo.getTabuleiro().getPosicoes().length; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(9); // gera um número aleatório entre 0 e 8
            } while (generatedNumbers.contains(randomNumber));
            jogo.getTabuleiro().getPosicoes()[i] = randomNumber;
            generatedNumbers.add(randomNumber);
        }
        generatedNumbers.clear();
    }


    public void atualizarTabuleiro() {
        for (int i = 0; i < jogo.getTabuleiro().getPosicoes().length; i++) {
            JButton botao = jogo.getBotoes()[i];
            int valor = jogo.getTabuleiro().getPosicoes()[i];
            if (valor == 0) {
                botao.setText("");
            } else {
                botao.setText(String.valueOf(valor));
            }
        }
    }

    public boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < jogo.getTabuleiro().getPosicoes().length; i++) {
            if (jogo.getTabuleiro().getPosicoes()[i] != count % 9) {
                return false;
            }
            count++;
        }
        return true;
    }

    public void reiniciarJogo() {
        gerarTabuleiro();
        atualizarTabuleiro();
    }


    public void mover(int posicao) {
        int[] tabuleiro = jogo.getTabuleiro().getPosicoes();
        JButton[] botoes = jogo.getBotoes();
        int tamanhoTabuleiro = tabuleiro.length;

        if (posicao + 1 < tamanhoTabuleiro && tabuleiro[posicao + 1] == 0) {
            JButton botaoAtual = botoes[posicao];
            JButton botaoSucessor = botoes[posicao + 1];
            int valorBotaoAtual = tabuleiro[posicao];
            tabuleiro[posicao + 1] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            tabuleiro[posicao] = 0;
            botaoAtual.setText("");
        } else if (posicao - 1 >= 0 && tabuleiro[posicao - 1] == 0) {
            JButton botaoAtual = botoes[posicao];
            JButton botaoSucessor = botoes[posicao - 1];
            int valorBotaoAtual = tabuleiro[posicao];
            tabuleiro[posicao - 1] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            tabuleiro[posicao] = 0;
            botaoAtual.setText("");
        } else if (posicao + 3 < tamanhoTabuleiro && tabuleiro[posicao + 3] == 0) {
            JButton botaoAtual = botoes[posicao];
            JButton botaoSucessor = botoes[posicao + 3];
            int valorBotaoAtual = tabuleiro[posicao];
            tabuleiro[posicao + 3] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            tabuleiro[posicao] = 0;
            botaoAtual.setText("");
        } else if (posicao - 3 >= 0 && tabuleiro[posicao - 3] == 0) {
            JButton botaoAtual = botoes[posicao];
            JButton botaoSucessor = botoes[posicao - 3];
            int valorBotaoAtual = tabuleiro[posicao];
            tabuleiro[posicao - 3] = valorBotaoAtual;
            botaoSucessor.setText(String.valueOf(valorBotaoAtual));
            tabuleiro[posicao] = 0;
            botaoAtual.setText("");
        }


        if (jogoConcluido()) {
            JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
            reiniciarJogo();
        }
    }

    public JButton[] getBotoes() {
        return jogo.getBotoes();
    }

    public void setBotaoReiniciar(JButton botaoReiniciar) {
        jogo.setBotaoReiniciar(botaoReiniciar);
    }
}