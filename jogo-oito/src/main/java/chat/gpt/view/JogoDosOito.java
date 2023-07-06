package chat.gpt.view;

import chat.gpt.model.JogoModel;
import chat.gpt.model.TabuleiroModel;
import chat.gpt.service.JogoServiceImpl;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class JogoDosOito extends JFrame {

    private final JogoModel jogo;
    private final JogoServiceImpl service;
    private final TabuleiroModel tabuleiro;

    public JogoDosOito() {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        // singleton
        this.tabuleiro = new TabuleiroModel();
        this.jogo = new JogoModel(tabuleiro);
        this.service = new JogoServiceImpl(jogo);

        // setando quantidade de botoes
        this.jogo.setBotoes(new JButton[9]);
        this.jogo.getTabuleiro().setPosicoes(new Integer[9]);

        this.service.gerarTabuleiro();
        this.gerarBotoes();
    }


    // gerar e atribuir eventos aos botoes
    private void gerarBotoes() {
        for (int i = 0; i < jogo.getTabuleiro().getPosicoes().length; i++) {
            int posicao = i;

            JButton botao = new JButton();
            botao.setFont(new Font("Arial", Font.BOLD, 36));
            botao.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    service.mover(posicao);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            service.getBotoes()[i] = botao;
            add(botao);
        }

        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                service.reiniciarJogo();
            }
        });
        service.setBotaoReiniciar(botaoReiniciar);

        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));

        setFocusable(true);
        setVisible(true);
        service.atualizarTabuleiro();
    }

}