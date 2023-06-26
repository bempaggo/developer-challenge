package chat.gpt.view;

import chat.gpt.model.TabuleiroDoJogo;

import javax.swing.*;
import java.awt.*;

public class JogoDosOito extends JFrame {

    private final transient TabuleiroDoJogo tabuleiroDoJogo;

    public JogoDosOito() {
        configurarJanela();
        tabuleiroDoJogo = new TabuleiroDoJogo(this);
        adicionarBotaoReiniciar();
        setVisible(true);
        setFocusable(true);
    }

    private void configurarJanela() {
        setTitle("Jogo dos Oito");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
    }

    private void adicionarBotaoReiniciar() {
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> tabuleiroDoJogo.reiniciarJogo());
        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));
    }

}
