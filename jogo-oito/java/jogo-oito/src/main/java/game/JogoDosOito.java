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

public class JogoDosOito extends JFrame implements KeyListener {
	private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private JButton[][] botoes = new JButton[3][3];
	private JButton botaoReiniciar;
	private Actions actions;

	public JogoDosOito() {
		super("Jogo dos Oito");

		configWindow();
		renderButtons();
		setActions();

		addKeyListener(this);
		setFocusable(true);

		this.actions = new Actions(this);

		actions.atualizarTabuleiro();
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public JButton[][] getBotoes() {
		return botoes;
	}

	public void setTabuleiro(int x, int y, int value) {
		this.tabuleiro[x][y] = value;
	}

	public void replaceTabuleiro(int[][] value) {
		tabuleiro = value;
	}

	private void configWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		setVisible(true);
	}

	private void renderButtons() {
		Font font = new Font("Arial", Font.BOLD, 36);

		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				JButton botao = new JButton();
				botao.setFont(font);

				botoes[linha][coluna] = botao;
				add(botao);
			}
		}

		botaoReiniciar = new JButton("Reiniciar");

		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));
	}

	private void setActions() {
		// Adiciona ação ao botão reiniciar
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.reiniciarJogo();
			}
		});
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
			case KeyEvent.VK_UP:
				actions.mover(1, 0);
				break;
			case KeyEvent.VK_DOWN:
				actions.mover(-1, 0);
				break;
			case KeyEvent.VK_LEFT:
				actions.mover(0, 1);
				break;
			case KeyEvent.VK_RIGHT:
				actions.mover(0, -1);
				break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
