package view;

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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.Controller;
import service.Vertex;

public class JogoDosOito extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1022620956191549378L;
	
	private final List<JButton> buttons;
    private final Controller controller;
    private JButton reset;
    private JButton feedback;
    private JButton instructions;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.controller.setting();
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
    	
    	if(text.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "Deve clicar no número para mover para o espaço vazio!");
        	return 0;
    	}	
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

    private void configMenu() {
        this.reset = this.configReset();
        this.feedback = this.configFeedback();
        this.instructions = this.configInstructions();
        add(this.feedback);
        add(this.reset);
        add(instructions);
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
        controller.setting();
        updateBoard();
    }
    
    private void showFeedback() {
        controller.feedback();
        updateBoard();
    }

    private void updateBoard() {
        List<Vertex> cells = controller.getCells();
        IntStream.range(0, cells.size())
                .forEach(index -> {
                    JButton button = buttons.get(index);
                    button.setText(cells.get(index).valueToText());
                });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        controller.swap(e.getKeyCode());
        updateBoard();
        checkGameOver();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private JButton configInstructions() {
        JButton buttonInstructions = new JButton("Instruções");
        buttonInstructions.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Regra: \n - Use os botões Up, Down, Left, Right, para mover os númeroa a extremidade do espaço vazio"
            		+ " e completar a sequencia como proposto no gabarito.");
            SwingUtilities.getRoot(buttonInstructions).requestFocus();
        });
        return buttonInstructions;
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.createButtons();
        game.configMenu();
        game.configureInterface();

    }
}
