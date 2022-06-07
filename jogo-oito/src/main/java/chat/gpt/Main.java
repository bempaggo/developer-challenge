package chat.gpt;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chat.gpt.domain.actions.MouseMoveAction;
import chat.gpt.domain.actions.MoveAction;
import chat.gpt.domain.listeners.KeyboardListener;
import chat.gpt.domain.listeners.ManagerListener;
import chat.gpt.domain.listeners.MouseListener;
import chat.gpt.domain.listeners.NotificationListener;
import chat.gpt.domain.search.Search;
import chat.gpt.domain.table.Table;
import chat.gpt.view.MainView;

public class Main {
    static final Integer WEIGTH = 300;
    static final Integer LENGTH= 300;

    public static void main(String[] args) {

        ManagerListener manager = new ManagerListener();

        KeyboardListener keyboardListener = manager.getListener(KeyboardListener.class);
        NotificationListener notificationListener = manager.getListener(NotificationListener.class);
        MouseListener mouseListener = manager.getListener(MouseListener.class);
        
        var table = new Table(notificationListener);

        var game = new MainView(table, manager);

        keyboardListener
        .subscribe(KeyEvent.VK_UP, new MoveAction(table, 1, 0))
        .subscribe(KeyEvent.VK_DOWN, new MoveAction(table, -1, 0))
        .subscribe(KeyEvent.VK_LEFT, new MoveAction(table, 0, 1))
        .subscribe(KeyEvent.VK_RIGHT, new MoveAction(table, 0, -1))
        
        .subscribe(KeyEvent.VK_W, new MoveAction(table, 1, 0))
        .subscribe(KeyEvent.VK_S, new MoveAction(table, -1, 0))
        .subscribe(KeyEvent.VK_A, new MoveAction(table, 0, 1))
        .subscribe(KeyEvent.VK_D, new MoveAction(table, 0, -1))

        .subscribe(KeyEvent.VK_ESCAPE, x -> {
            notificationListener.notify("restart", "restart");
        });

        notificationListener.subscribe("jogoConcluido", x -> {
            JOptionPane.showMessageDialog(game, x);
        }).subscribe("restart", x -> {

            // Search search =new Search();
            // search.findPath(table);
            // table.suffleTable();
        });

        mouseListener
        .subscribe("move", new MouseMoveAction(table));



        game.setSize(WEIGTH, LENGTH);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(300, 300);
		game.setLayout(new GridLayout(4, 3));

        game.start();
    }
}
