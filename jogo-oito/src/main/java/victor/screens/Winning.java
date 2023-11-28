package victor.screens;

import javax.swing.*;

import victor.managers.ScreenManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Winning extends JFrame implements KeyListener {
    public Winning(ScreenManager screenManager) {
        super("Welcome");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 300);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel winningLabel = new JLabel("Parabéns, você conseguiu concluir o jogo!");
        winningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(winningLabel, c);

        JLabel tries = new JLabel("Você precisou de " + screenManager.getGameManager().getClickQuantity() + " tentativas " + "e " + screenManager.getGameManager().getSecondsToResolve() + " segundos");
        tries.setHorizontalAlignment(SwingConstants.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(tries, c);

        JButton inicialButton = new JButton();
        inicialButton.setText("Tela inicial");
        inicialButton.setHorizontalAlignment(SwingConstants.CENTER);
        inicialButton.addActionListener(screenManager);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(inicialButton, c);

        addKeyListener(this);
        setFocusable(true);

        setVisible(true);
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
