package view;

import facade.Controller;
import interfaces.Vertex;
import listeners.BoardUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class JogoDosOito extends JFrame implements KeyListener, BoardUpdateListener {

    private final List<Button> buttons;
    private final Controller controller;

    public JogoDosOito(Controller controller) {
        super("Jogo dos Oito");
        this.controller = controller;
        this.buttons = new ArrayList<>();
    }

    public void configureInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    public void createButtons() {
        this.controller.getCells().forEach(cell -> {
            Button button = this.configButton(cell);
            add(button);
            buttons.add(button);
        });
    }

    private Integer textToValue(String text) {
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    private Button configButton(Vertex cell) {
        return new Button()
                .withFont(new Font("Arial", Font.BOLD, 36))
                .withText(cell.valueToText())
                .withActionListener((ActionEvent e) -> {
                    this.controller.buttonClicked(textToValue(e.getActionCommand()));
                    this.checkGameOver();
                });
    }

    private void checkGameOver() {
        Optional.ofNullable(this.controller.isGameComplete())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    public void configMenu() {
        add(this.configFeedback());
        add(this.configReset());
        add(new JLabel(""));
    }

    private Button configReset() {
        return new Button("Reiniciar")
                .withActionListener((ActionEvent e) -> this.resetGame());
    }

    private Button configFeedback() {
        return new Button("Gabarito")
                .withActionListener((ActionEvent e) -> this.showSolution());
    }


    private void resetGame() {
        this.controller.initializeBoard();
    }

    private void showSolution() {
        this.controller.setBoardAsSolved();
    }

    @Override
    public void updateBoard() {
        List<Vertex> cells = this.controller.getCells();
        IntStream.range(0, cells.size())
                .forEach(index -> {
                    Button button = this.buttons.get(index);
                    button.setText(cells.get(index).valueToText());
                });
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.controller.keyPressed(e.getKeyCode());
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
