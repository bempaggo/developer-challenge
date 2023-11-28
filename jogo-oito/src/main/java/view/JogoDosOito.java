package view;

import facade.Controller;
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

/**
 * Classe responsável por gerenciar a interface gráfica do jogo.
 *
 * @author quintino
 */
public class JogoDosOito extends JFrame implements KeyListener {
    private final List<JButton> buttons = new ArrayList<>();
    private final Controller controller;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.controller.getCells().forEach(this::configButton);
        this.configReset();
        this.configFeedback();
        add(new JLabel(""));
        this.configureInterface();
    }

    private void configureInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    private Integer textToValue(String text) {
        return Optional.ofNullable(text)
                .map(Integer::valueOf)
                .orElse(0);
    }

    private void configButton(Vertex cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
        button.addActionListener((ActionEvent e) -> {
            this.controller.click(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkGameOver();
            SwingUtilities.getRoot(button).requestFocus();
        });
        add(button);
        buttons.add(button);
    }

    private void checkGameOver() {
        Optional.ofNullable(this.controller.checkGameOver())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    private void configReset() {
        JButton button = new JButton("Reiniciar");
        button.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(button).requestFocus();
        });
        add(button);
    }

    private void configFeedback() {
        JButton button = new JButton("Gabarito");
        button.addActionListener((ActionEvent e) -> {
            this.controller.feedback();
            this.updateBoard();
            SwingUtilities.getRoot(button).requestFocus();
        });
        add(button);
    }


    private void resetGame() {
        this.controller.setting();
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
        //TODO document why this method is empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.controller.swap(e.getKeyCode());
        this.updateBoard();
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //TODO document why this method is empty
    }

    public static void main(String[] args) {
        new JogoDosOito();
    }
}
