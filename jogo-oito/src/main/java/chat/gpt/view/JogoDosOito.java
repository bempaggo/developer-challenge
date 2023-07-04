package chat.gpt.view;

import chat.gpt.model.JogoModel;
import chat.gpt.service.JogoService;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class JogoDosOito extends JFrame implements KeyListener {

    private final JogoModel jogo = new JogoModel();

    private final JogoService service = new JogoService(jogo);

    public JogoDosOito() {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        service.gerarTabuleiro();
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

        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        service.atualizarTabuleiro();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
