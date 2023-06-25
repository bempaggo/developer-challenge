package chat.gpt.controller;

import java.awt.event.*;
import javax.swing.*;

import chat.gpt.model.GameModel;
import chat.gpt.view.GameView;

public class GameController implements KeyListener, ActionListener, MouseListener  {

    private GameModel model;
    private GameView view;

    // Construtor responsável por iniciar e configurar o controller do jogo
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        // Adiciona ouvinte de eventos do teclado
        this.view.addKeyListener(this);
        // Adiciona view do botão de reiniciar e o metodo adiciona o proprio controlador como ouvinte
        this.view.getBotaoReiniciar().addActionListener(this);

        // Captura eventos do mouse nos botões individuais e responda
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = this.view.getBotoes(i, j);
                button.addMouseListener(this);
            }
        }

        // Atualiza a exibição do jogo
        this.atualizarView();
    }

    // Metodo que lida com ações de click reiniciando
    @Override
    public void actionPerformed(ActionEvent e) {
        // Chama o metodo reiniciar quando clicado
        this.model.reiniciar();
        // Atualiza a interface após clicado
        this.atualizarView();
    }

    // Metodo que é chamado quando as teclas (Setas) do teclado é pressionada
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // Case obtém a tecla pressionada
            case KeyEvent.VK_UP:
                // Com base na tecla pressionada realiza a ação de mover, do metodo mover
                this.model.mover(1, 0);
                break;
            case KeyEvent.VK_DOWN:
                this.model.mover(-1, 0);
                break;
            case KeyEvent.VK_LEFT:
                this.model.mover(0, 1);
                break;
            case KeyEvent.VK_RIGHT:
                this.model.mover(0, -1);
                break;
        }
        this.atualizarView();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Sem ações
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Sem ações
    }

    // Atualiza a exibição do jogo
    private void atualizarView() {
        // Recebe matriz do jogo atual
        int[][] tabuleiro = model.getTabuleiro();
        // Chamada do metodo para atualizar o tabuleiro
        view.atualizarTabuleiro(tabuleiro);
        // repaint redesenha a tela e exibe as atualizações feitas
        view.repaint();
    }

    // Acionado quando um botão do mouse é clicado
    @Override
    public void mouseClicked(MouseEvent e) {
        // Armazena o botao cliclado na variavel botaoClicado
        JButton botaoClicado = (JButton) e.getSource();
        int linha = -1;
        int coluna = -1;
        // Encontra a linha e a coluna do botão clicado
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.view.getBotoes(i, j) == botaoClicado) {
                    linha = i;
                    coluna = j;
                }
            }
        }
        // Move o número para o quadrado vazio
        this.model.mover(linha - this.model.getLinhaVazia(), coluna - this.model.getColunaVazia());
        // Atualiza a exibição do jogo
        this.atualizarView();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Sem ações
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Sem ações
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Sem ações
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Sem ações
    }
}
