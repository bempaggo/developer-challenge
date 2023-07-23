package view;

import interfaces.Graph;
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
    private final Graph board;
    private JButton reset;
    private JButton feedback;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.board = new Board();
        this.board.setUpNewBoard();
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
        this.board.getCells().forEach(cell -> {
            JButton button = this.configButton(cell);
            add(button);
            buttons.add(button);
        });
    }

    private Integer textToValue(String text) {
        return Integer.valueOf(text);
    }

    // TODO: trouxe essa feiura pra cá, vou arrumar isso com uma subclasse para botão
    private JButton configButton(Vertex cell) {
        JButton button = new JButton() {
            @Override
            public void setText(String text) {
                // Configura o texto do botão apenas se o valor for diferente de "0"
                super.setText("0".equals(text) ? "" : text);
            }
        };
        
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
    
        button.addActionListener((ActionEvent e) -> {
            this.board.moveCellByValue(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkGameOver();
            SwingUtilities.getRoot(button).requestFocus();
        });
    
        return button;
    }
    

    private void configMenu() {
        this.reset = this.configResetGameButton();
        this.feedback = this.configGameSolutionButton();
        add(this.feedback);
        add(this.reset);
        add(new JLabel(""));
    }

    private JButton configResetGameButton() {
        JButton buttonReset = new JButton("Reiniciar");
        buttonReset.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(buttonReset).requestFocus();
        });
        return buttonReset;
    }
    
    private JButton configGameSolutionButton() {
        JButton buttonFeedback = new JButton("Gabarito");
        buttonFeedback.addActionListener((ActionEvent e) -> {
            this.showGameSolution();
            SwingUtilities.getRoot(buttonFeedback).requestFocus();
        });
        return buttonFeedback;
    }

    private void checkGameOver() {
        Optional.ofNullable(this.board.checkGameOver())
                .filter(Boolean::booleanValue)
                .ifPresent(gameOver -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    resetGame();
                });
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
        List<Vertex> cells = this.board.getCells();
        IntStream.range(0, cells.size())
                .forEach(index -> {
                    JButton button = this.buttons.get(index);
                    button.setText(cells.get(index).valueToText());
                });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // alguma coisa esquisita aqui no keyPressed, mas eu não sei dizer o que é
    @Override
    public void keyPressed(KeyEvent e) {
        this.board.moveCellByKey(e.getKeyCode());
        this.updateBoard();
        this.checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.createButtons();
        game.configMenu();
        game.configureInterface();

    }
}
