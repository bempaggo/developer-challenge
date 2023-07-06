package view;

import facade.Controller;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Cell;

public class JogoDosOito extends JFrame implements KeyListener {

    private final List<JButton> buttons;
    private final Controller controller;
    private JButton reset;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.controller.configBoard();
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
        JButton button;
        List<Cell> cells = this.controller.getCells();
        for (Cell cell : cells) {
            button = this.configButton(cell);
            add(button);
            this.buttons.add(button);
        }
    }

    private Integer textToValue(String text) {
        return text.equals("") ? 0 : Integer.valueOf(text);
    }

    private JButton configButton(Cell cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
        button.addActionListener((ActionEvent e) -> {
            this.controller.swap(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkGameOver();
            SwingUtilities.getRoot(button).requestFocus();
        });
        return button;

    }

    private void checkGameOver() {
        if (this.controller.checkGameOver()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            this.resetGame();
        }
    }

    private void configReset() {
        this.reset = new JButton("Reiniciar");
        this.reset.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(this.reset).requestFocus();
        });
        add(new JLabel(""));
        add(this.reset);
        add(new JLabel(""));
    }

    private void resetGame() {
        this.controller.configBoard();
        this.updateBoard();
    }

    private void updateBoard() {
        Integer index = 0;
        for (Cell cell : this.controller.getCells()) {
            JButton button = this.buttons.get(index);
            button.setText(cell.valueToText());
            index += 1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.controller.click(e.getKeyCode());
        this.updateBoard();
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.createButtons();
        game.configReset();
        game.configureInterface();

    }
}
