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
import chat.gpt.domain.search.Search;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MainView extends JFrame {

	private JButton botaoReiniciar;
	private Table table;
	private ManagerListener listener;

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
				Search search =new Search();
            	search.findPath(table);
				listener.getListener(NotificationListener.class)
						.notify("restart", "restart");
			}
		});

		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));

		setFocusable(true);
		requestFocus();
		addKeyListener(listener.getListener(KeyboardListener.class));
		setVisible(true);
	}
}
