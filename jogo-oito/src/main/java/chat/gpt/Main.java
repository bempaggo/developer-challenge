package chat.gpt;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JButton[][] botoes = new JButton[3][3];
        
        var listener = new Listener();
        var gameListener = new GameListener();

        var table = new Table(gameListener);

        listener
                .subscribe(KeyEvent.VK_UP, new MoveAction(table, 1, 0))
                .subscribe(KeyEvent.VK_DOWN, new MoveAction(table, -1, 0))
                .subscribe(KeyEvent.VK_LEFT, new MoveAction(table, 0, 1))
                .subscribe(KeyEvent.VK_RIGHT, new MoveAction(table, 0, -1))
                .subscribe(KeyEvent.VK_ESCAPE, x -> {
                    System.out.println("ESC " + " - Keycode: "+ x);
                });


        gameListener.subscribe("jogoConcluido", x -> {
            // JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            JOptionPane.showMessageDialog(null, x);

        });

        var game = new JogoDosOito(table, listener, gameListener);
        game.start();
    }
}
