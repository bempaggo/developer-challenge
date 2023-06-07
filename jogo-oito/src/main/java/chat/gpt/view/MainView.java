package chat.gpt.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chat.gpt.domain.listeners.KeyboardListener;
import chat.gpt.domain.listeners.ManagerListener;
import chat.gpt.domain.listeners.MouseListener;
import chat.gpt.domain.listeners.NotificationListener;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MainView extends JFrame {

	private JButton botaoReiniciar;
	private JButton solvePuzzleButton;
	private Table table;
	private ManagerListener listener;
	private JLabel bannerLabel = new JLabel("");

	public MainView(Table table, ManagerListener listener) {
		super("Jogo dos Oito");
		this.table = table;
		this.listener = listener;
	}

	public void start() {
		for (TableCell cell : table.getCells()) {
			cell.setFont(new Font("Arial", Font.BOLD, 36));
			cell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.getListener(MouseListener.class)
							.notify("move", cell);
				}
			});
			add(cell);
		}

		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.getListener(NotificationListener.class)
						.notify("restart", "restart");
			}
		});

		solvePuzzleButton = new JButton("Resolver");
		solvePuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.getListener(NotificationListener.class)
						.notify("solve", "disable");
			}
		});

		add(botaoReiniciar);
		add(solvePuzzleButton);
		add(new JLabel(""));
		add(new JLabel(""));
		add(bannerLabel);

		setFocusable(true);
		requestFocus();
		addKeyListener(listener.getListener(KeyboardListener.class));
		setVisible(true);
	}

	public void showMessage(String message) {
		bannerLabel.setText(message);
	}
}
