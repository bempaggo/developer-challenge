package chat.gpt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoListener implements KeyListener {

    private Tabuleiro tabuleiro;

    public TecladoListener(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                tabuleiro.mover(1, 0);
                break;
            case KeyEvent.VK_DOWN:
                tabuleiro.mover(-1, 0);
                break;
            case KeyEvent.VK_LEFT:
                tabuleiro.mover(0, 1);
                break;
            case KeyEvent.VK_RIGHT:
                tabuleiro.mover(0, -1);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
