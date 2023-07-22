package view;

import facade.Controller;
import interfaces.Vertex;
import model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JogoDosOito extends JFrame implements KeyListener {

    private final List<JButton> buttons;
    private final Controller controller;
    private JButton reset;
    private JButton feedback;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.controller.setting();
        this.buttons = new ArrayList<>();
    }

    public void createButtons() {
        for (int index = 0; index < this.getCells().size(); index++) {
            Vertex cell = this.getCells().get(index);
            this.createButton(cell);
        }
    }

    public void configureMenu() {
        this.reset = this.configureReset();
        this.feedback = this.configureFeedback();
        add(this.feedback);
        add(this.reset);
        add(new JLabel(""));
    }

    public void configureInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
        centerFrameInTheScreen(this);
    }

    public List<JButton> getButtons() {
        return this.buttons;
    }

    public JButton getReset() {
        return this.reset;
    }

    public JButton getFeedback() {
        return this.feedback;
    }

    private List<Vertex> getCells() {
        return this.controller.getCells();
    }

    private void createButton(Vertex cell) {
        JButton button = this.configureButton(cell);
        add(button);
        buttons.add(button);
    }

    private Integer textToValue(String text) {
        return Optional.ofNullable(text)
                .filter(textValue -> textValue != "")
                .map(Integer::valueOf)
                .orElse(0);
    }

    private JButton configureButton(Vertex cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
        button.addActionListener((ActionEvent e) -> {
            this.controller.click(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkGameOver();
            SwingUtilities.getRoot(button).requestFocus();
        });
        return button;
    }

    private void checkGameOver() {
        Optional.ofNullable(this.controller.checkGameOver())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    private JButton configureReset() {
        JButton buttonReset = new JButton("Reiniciar");
        buttonReset.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(buttonReset).requestFocus();
        });
        return buttonReset;
    }
    
    private JButton configureFeedback() {
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
        List<Vertex> cells = getCells();
        for (int index = 0; index < cells.size(); index++) {
            JButton button = this.buttons.get(index);
            button.setText(Cell.valueToText(cells, index));
        }
    }

    private void centerFrameInTheScreen(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        frame.setLocation(x, y);
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.updateBoard();
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

}
