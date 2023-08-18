package view;

import facade.Controller;
import interfaces.BoardUpdateListener;
import interfaces.Vertex;
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

public class JogoDosOito extends JFrame implements KeyListener, BoardUpdateListener {

    private final List<JButton> buttons;
    private final Controller controller;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.controller.gameStartBoardState();
        this.buttons = new ArrayList<>();
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
            this.checkGameOver();
            SwingUtilities.getRoot(button).requestFocus();
        });
        return button;

    }

    private void checkGameOver() {
        Optional.ofNullable(this.controller.isGameComplete())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    private void configMenu() {
        JButton reset = this.configReset();
        JButton feedback = this.configFeedback();
        add(feedback);
        add(reset);
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
        this.controller.gameStartBoardState();
    }
    
    private void showFeedback() {
        this.controller.gameSolutionBoardState();
    }

    @Override
    public void updateBoard() {
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
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.controller.getBoard().addListener(game);
        game.createButtons();
        game.configMenu();
        game.configureInterface();
    }
}
