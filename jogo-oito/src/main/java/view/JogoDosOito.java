package view;

import facade.Controller;
import interfaces.Vertex;
import util.Board;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class JogoDosOito extends JFrame implements KeyListener {

    private final List<JButton> buttons;
    private final Controller controller;
    private JButton reset;
    private JButton feedback;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller(new Board());
        this.controller.setting();
        this.buttons = new ArrayList<>();
        createButtons();
        configMenu();
        configureInterface();
    }

    private void configureInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    private void createButtons() {
        this.controller.getCells().forEach(cell -> {
            JButton button = this.configButton(cell);
            add(button);
            buttons.add(button);
        });
    }

    private Integer textToValue(String text) {
        return Optional.ofNullable(text)
                .map(Integer::valueOf)
                .orElse(0);
    }

    private JButton configButton(Vertex cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
        button.addActionListener((ActionEvent e) -> {
            this.controller.click(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkVictory();
            SwingUtilities.getRoot(button).requestFocus();
        });
        return button;
    }

    private void checkVictory() {
        Optional.ofNullable(this.controller.checkVicory())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    private void configMenu() {
        this.reset = this.configReset();
        this.feedback = this.configFeedback();
        add(this.feedback);
        add(this.reset);
        add(new JLabel(""));
    }

    private JButton configReset() {
        JButton buttonReset = new JButton("Reiniciar");
        buttonReset.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(buttonReset).requestFocus();
        });
        return buttonReset;
    }
    
    private JButton configFeedback() {
        JButton buttonFeedback = new JButton("Gabarito");
        buttonFeedback.addActionListener((ActionEvent e) -> {
            this.showFeedback();
            SwingUtilities.getRoot(buttonFeedback).requestFocus();
        });
        return buttonFeedback;
    }


    private void resetGame() {
        this.controller.setting();
        this.updateBoard();
    }
    
    private void showFeedback() {
        this.controller.feedback();
        this.updateBoard();
    }

    private void updateBoard() {
        List<Vertex> cells = this.controller.getCells();
        IntStream.range(0, cells.size())
                .forEach(index -> {
                    JButton button = this.buttons.get(index);
                    button.setText(cells.get(index).valueToText());
                });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.controller.swap(e.getKeyCode());
        this.updateBoard();
        this.checkVictory();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
