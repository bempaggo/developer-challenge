package chat.gpt;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class Main {
    public static void main(String[] args) {
        JButton[][] botoes = new JButton[3][3];

        var table = new Table(botoes);
        var listener = new Listener();

        listener
                .subscribe(KeyEvent.VK_UP, new MoveAction(table, 1, 0))
                .subscribe(KeyEvent.VK_DOWN, new MoveAction(table, -1, 0))
                .subscribe(KeyEvent.VK_LEFT, new MoveAction(table, 0, 1))
                .subscribe(KeyEvent.VK_RIGHT, new MoveAction(table, 0, -1));

        var game = new JogoDosOito(table, listener);
        game.start();
    }
}
