package chat.gpt;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    static final Integer LENGTH = 300;

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
            table.randomizeTable();
        }).subscribe("solve", x -> {
            ExecutorService threadpool = Executors.newCachedThreadPool();
            threadpool.submit(() -> {
                try {
                    game.showMessage("Aguarde, resolvendo o 8-puzzle...");

                    manager.setEnabledEvents(false);

                    Search search = new Search();
                    var path = search.findPath(table);

                    for (var step : path) {
                        System.out.println(step[0] + ", " + step[1]); // logs movs
                        var action = new MoveAction(table, step[0], step[1]);
                        action.doAction();
                        Thread.sleep(1000);

                    }
                    
                    manager.setEnabledEvents(true);
                    notificationListener.notify("jogoConcluido",
                            String.format("O 8-Puzzle foi resolvido com o A* em %s movimentos", path.size()));
                    game.showMessage("");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        });

        mouseListener
                .subscribe("move", new MouseMoveAction(table));

        game.setSize(WEIGTH, LENGTH);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(375, 375);
        game.setLayout(new GridLayout(5, 3));

        game.start();
    }
}
