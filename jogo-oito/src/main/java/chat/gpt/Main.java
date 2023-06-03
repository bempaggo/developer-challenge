package chat.gpt;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

public class Main {
    static final Integer WEIGTH = 300;
    static final Integer LENGTH= 300;

    public static void main(String[] args) {

        var listener = new Listener();
        var gameListener = new GameListener();
        var table = new Table(gameListener);

        var game = new JogoDosOito(table, listener, gameListener);

        listener
        .subscribe(KeyEvent.VK_UP, new MoveAction(table, 1, 0))
        .subscribe(KeyEvent.VK_DOWN, new MoveAction(table, -1, 0))
        .subscribe(KeyEvent.VK_LEFT, new MoveAction(table, 0, 1))
        .subscribe(KeyEvent.VK_RIGHT, new MoveAction(table, 0, -1))
        
        .subscribe(KeyEvent.VK_W, new MoveAction(table, 1, 0))
        .subscribe(KeyEvent.VK_S, new MoveAction(table, -1, 0))
        .subscribe(KeyEvent.VK_A, new MoveAction(table, 0, 1))
        .subscribe(KeyEvent.VK_D, new MoveAction(table, 0, -1))

        .subscribe(KeyEvent.VK_ESCAPE, x -> {
            gameListener.notify("restart", "restart");
        });

        gameListener.subscribe("jogoConcluido", x -> {
            // JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            JOptionPane.showMessageDialog(game, x);
        }).subscribe("restart", x -> {
            table.suffleTable();
        });


        game.setSize(WEIGTH, LENGTH);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(300, 300);
		game.setLayout(new GridLayout(4, 3));

        game.start();
    }
}
