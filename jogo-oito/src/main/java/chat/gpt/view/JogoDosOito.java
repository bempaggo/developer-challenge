package chat.gpt.view;

import chat.gpt.model.JogoDosOitoModel;
import chat.gpt.service.JogoDosOitoService;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JogoDosOito extends JFrame implements KeyListener {

    JogoDosOitoModel jogo = new JogoDosOitoModel();

    private JogoDosOitoService service = new JogoDosOitoService(jogo);

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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int indexLinha = i;
                int indexColuna = j;

                JButton botao = new JButton();
                botao.setFont(new Font("Arial", Font.BOLD, 36));
                botao.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        service.mover(indexLinha, indexColuna);
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
                service.getBotoes()[i][j] = botao;
                add(botao);
            }
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
