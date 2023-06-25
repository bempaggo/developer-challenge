package chat.gpt.view;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameView extends JFrame {

    private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    private JButton[][] botoes = new JButton[3][3];
    private final JButton botaoReiniciar;

    // Cria e configura a interface gráfica do jogo
    public GameView() {
        // Insere o titulo na view
        super("Jogo dos Oito");
        // Define o comportamento ai fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Define o tamanho da view
        setSize(300, 350);

        // Cria painel que organiza os componentes nas 5 regioes (norte, sul, leste, oeste e centro)
        JPanel panel = new JPanel(new BorderLayout());

        // Cria o layout com os botoes 3x3
        JPanel botoesPanel = new JPanel(new GridLayout(3, 3));

        // Cria as configurações dos botões individualmente
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = new JButton();
                // Configurações da fonte
                botao.setFont(new Font("Consolas", Font.PLAIN, 20));
                botoes[i][j] = botao;
                // Adiciona o botao no painel depois de configurados.
                botoesPanel.add(botao);
            }
        }

        // Adiciona todos os botões criados ao centro do painel
        panel.add(botoesPanel, BorderLayout.CENTER);

        // Cria botao reiniciar e define que sera apresentado com letras maiusculas
        botaoReiniciar = new JButton("Reiniciar".toUpperCase());
        // Configurações da fonte (Tipo, estilo e tamanho)
        botaoReiniciar.setFont(new Font("Comic Sans", Font.BOLD, 20));
        // Define o tamanho do botão
        botaoReiniciar.setPreferredSize(new Dimension(botaoReiniciar.getPreferredSize().width * 2, botaoReiniciar.getPreferredSize().height * 2));
        // Adiciona o botãi reinciar ao Sul da view
        panel.add(botaoReiniciar, BorderLayout.SOUTH);

        // Define o painel principal
        setContentPane(panel);
        // Desabilita o redimensionamento de tela
        setResizable(false);
        // Centraliza a view no centro da tela
        setLocationRelativeTo(null);
        // Torna a janela vizivel
        setVisible(true);
    }

    // Atualiza a exibição do tabuleiro
    public void atualizarTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;

        int count = 0;
        // Percorre a matriz. Para cada posição o texto no botão é definido como o valor da posição na matriz
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // faz a inserção do valor no botão "Atualizando"
                botoes[i][j].setText(String.valueOf(tabuleiro[i][j]));

                // Se o valor no botão for == 0 recebe um string em branco ""
                if (tabuleiro[i][j] == 0) {
                    botoes[i][j].setText("");
                }
            }
        }
    }

    // Ouvinte do teclado tanto para a janela principal do jogo quanto para cada um dos botões individuais
    public void addKeyListener(KeyListener listener) {
        super.addKeyListener(listener);
        // adiciona o ouvinte de eventos de teclado a cada botão individual na matriz
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].addKeyListener(listener);
            }
        }
    }

    // Getter reponsavel por retornar o botão de reiniciar
    public JButton getBotaoReiniciar() {
        return botaoReiniciar;
    }

    // Getter reponsavel por retornar o botao localizado na posiçãp [i, j] da matriz botoes
    public JButton getBotoes(int i, int j) {
        return botoes[i][j];
    }
}
