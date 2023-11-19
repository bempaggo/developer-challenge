package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import facade.GameController;
import interfaces.GameVertex;

public class JogoDosOito extends JFrame implements KeyListener {

	private final List<JButton> buttons;
	private final GameController controller;
	private JButton reset;
	private JButton feedback;

	public JogoDosOito() {
		super("Jogo dos Oito");
		this.controller = new GameController();
		this.controller.setupGame();
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
		this.controller.getGameCells().forEach(cell -> {
			JButton button = this.configureButton(cell);
			add(button);
			buttons.add(button);
		});
	}

	private Integer textToValue(String text) {
	    if (text != null && !text.isEmpty()) {
	        return Integer.valueOf(text);
	    }
	    JOptionPane.showMessageDialog(this, "Célula é vazia, por favor, selecione outra célula!", "Aviso", JOptionPane.ERROR_MESSAGE);

	    return 0;
	}


	private JButton configureButton(GameVertex cell) {
		JButton button = new JButton();
		button.setFont(new Font("Arial", Font.BOLD, 36));
		button.setText(cell.valueToString());
		button.addActionListener((ActionEvent e) -> {
			this.controller.processCellClick(this.textToValue(button.getText()));
			this.updateBoard();
			this.checkGameOver();
			SwingUtilities.getRoot(button).requestFocus();
		});
		return button;
	}

	private void checkGameOver() {
		if (this.controller.isGameOver()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			resetGame();
		}
	}

	private void configureMenu() {
		this.reset = this.configureReset();
		this.feedback = this.configureFeedback();
		add(this.feedback);
		add(this.reset);
		add(new JLabel(""));
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
		this.controller.setupGame();
		this.updateBoard();
	}

	private void showFeedback() {
		this.controller.provideFeedback();
		this.updateBoard();
	}

	private void updateBoard() {
		List<GameVertex> cells = this.controller.getGameCells();
		IntStream.range(0, cells.size()).forEach(index -> {
			JButton button = this.buttons.get(index);
			button.setText(cells.get(index).valueToString());
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.controller.performSwap(e.getKeyCode());
		this.updateBoard();
		this.checkGameOver();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		JogoDosOito game = new JogoDosOito();
		game.createButtons();
		game.configureMenu();
		game.configureInterface();
	}
}
