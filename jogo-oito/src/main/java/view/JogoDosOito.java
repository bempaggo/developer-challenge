package view;

import util.Board;

import javax.swing.*;

import interfaces.Graph;
import interfaces.Vertex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JogoDosOito extends JFrame implements KeyListener {

    private final List<JButton> buttons;
    private final Graph board;
    private JButton reset;
    private JButton feedback;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.board = new Board();
        this.board.setUpNewBoard();
        this.buttons = new ArrayList<>();
    }

    public void configUI() {
        configWindow();
        createButtons();
        configMenu();

        updateBoard();
        revalidate();
    }

    private void configWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    private void createButtons() {
        for (Vertex cell : this.board.getCells()) {
            ButtonBuilder button = configButton(cell);
            add(button);
            buttons.add(button);
        }
    }

    private ButtonBuilder configButton(Vertex cell) {
        ButtonBuilder button = new ButtonBuilder();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.withText(cell.valueToText());

        button.addActionListener((ActionEvent e) -> {
            this.board.moveCellByValue(Integer.valueOf(button.getText()));
            this.updateBoard();
            this.checkGameOver();
        });

        return button;
    }

    private void configMenu() {
        this.reset = configResetGameButton();
        this.feedback = configGameSolutionButton();
        add(this.feedback);
        add(this.reset);
        add(new JLabel(""));
    }

    private ButtonBuilder configResetGameButton() {
        ButtonBuilder buttonReset = new ButtonBuilder()
                .withText("Reiniciar")
                .withActionListener(e -> resetGame());
        return buttonReset;
    }

    private ButtonBuilder configGameSolutionButton() {
        ButtonBuilder buttonFeedback = new ButtonBuilder()
                .withText("Gabarito")
                .withActionListener(e -> showGameSolution());
        return buttonFeedback;
    }

    private void resetGame() {
        this.board.setUpNewBoard();
        this.updateBoard();
    }

    private void showGameSolution() {
        this.board.showSolvedBoard();
        this.updateBoard();
    }

    private void updateBoard() {
        for (Vertex cell : this.board.getCells()) {
            this.buttons.get(this.board.getCells().indexOf(cell))
                    .setText(cell.valueToText());
        }
    }

    private void checkGameOver() {
        Optional.ofNullable(this.board.checkGameOver())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.board.moveCellByKey(e.getKeyCode());
        updateBoard();
        checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.configUI();
    }
}
