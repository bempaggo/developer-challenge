package game;

// Java
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Javax 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameofEight extends JFrame implements KeyListener {
	private int[][] gameBoard = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private JButton[][] buttons = new JButton[3][3];
	private JButton restartButton;
	private Actions actions;

	public GameofEight() {
		super("Jogo dos Oito");

		configWindow();
		renderButtons();
		setActions();

		addKeyListener(this);
		setFocusable(true);

		this.actions = new Actions(this);

		actions.updateGameBoard();
	}

	public int[][] getGameBoard() {
		return gameBoard;
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public void setGameBoard(int x, int y, int value) {
		this.gameBoard[x][y] = value;
	}

	public void replaceGameBoard(int[][] value) {
		gameBoard = value;
	}

	private void configWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		setVisible(true);
	}

	private void renderButtons() {
		Font font = new Font("Arial", Font.BOLD, 36);

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				JButton button = new JButton();
				button.setFont(font);

				buttons[row][column] = button;
				add(button);
			}
		}

		restartButton = new JButton("Reiniciar");

		add(new JLabel(""));
		add(restartButton);
		add(new JLabel(""));
	}

	private void setActions() {
		// Adiciona ação ao botão reiniciar
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.restartGame();
			}
		});
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
			case KeyEvent.VK_UP:
				actions.move(1, 0);
				break;
			case KeyEvent.VK_DOWN:
				actions.move(-1, 0);
				break;
			case KeyEvent.VK_LEFT:
				actions.move(0, 1);
				break;
			case KeyEvent.VK_RIGHT:
				actions.move(0, -1);
				break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
