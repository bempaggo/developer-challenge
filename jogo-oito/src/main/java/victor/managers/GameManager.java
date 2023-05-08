package victor.managers;

import javax.swing.*;
import java.awt.*;

public class GameManager {
    private final int screenHeight;
    private final int screenWidth;

    private int clickQuantity = 0;

    private int seconds = 0;
    private int secondsToResolve;
    private Thread coutingThread;

    public BoardManager boardManager;

    public GameManager(int boardSize) {
        this.boardManager = new BoardManager(boardSize);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        screenHeight = Math.min(boardManager.getBoardSize() * 100, (int) width);
        screenWidth = Math.min(boardManager.getBoardSize() * 100, (int) height);
    }

    public int getClickQuantity() {
        return this.clickQuantity;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void addClick() {
        clickQuantity++;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public int getSecondsToResolve() {
        return this.secondsToResolve;
    }

    public void startCounting(JLabel timeLabel) {
        coutingThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    this.seconds++;
                    timeLabel.setText("Tempo: " + this.seconds + " segs");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        coutingThread.start();
    }

    public void stopCounting() {
        this.coutingThread.interrupt();

        this.secondsToResolve = this.seconds;
        this.seconds = 0;
    }

    public void restartGame() {
        this.clickQuantity = 0;
        this.seconds = 0;
        this.secondsToResolve = 0;
        this.boardManager.resetBoard();
    }
}

