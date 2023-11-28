package victor.screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import victor.managers.BoardManager;
import victor.managers.GameManager;
import victor.managers.ScreenManager;

public class Game extends JFrame implements KeyListener {
    private final ScreenManager screenManager;
    private final GameManager gameManager;
    private final BoardManager boardManager;

    private final JButton[][] buttons;
    private final JLabel clickQuantityLabel = new JLabel("Tentativas: 0");
    private final JLabel timeLabel = new JLabel("Tempo: 0 segs");

    public Game(ScreenManager screenManager) {
        super("Jogo dos Oito");
        this.screenManager = screenManager;
        this.gameManager = screenManager.getGameManager();
        this.boardManager = gameManager.boardManager;

        this.buttons = new JButton[boardManager.getBoardSize()][boardManager.getBoardSize()];

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(gameManager.getScreenWidth(), gameManager.getScreenHeight());
        setLocationRelativeTo(null);

        setLayout(new GridLayout(boardManager.getBoardSize() + 2, boardManager.getBoardSize()));

        gameManager.startCounting(this.timeLabel);

        addLabels();
        addButtons();

        addKeyListener(this);
        setFocusable(true);

        updateBoard();

        setVisible(true);
    }

    private void addLabels() {
        if (boardManager.getBoardSize() % 2 != 0) {
            add(timeLabel);
            add(clickQuantityLabel);

            addWhiteSpaces(boardManager.getBoardSize() - 2);
        } else {
            addWhiteSpaces(boardManager.getBoardSize() / 2 - 1);

            add(timeLabel);
            add(clickQuantityLabel);

            addWhiteSpaces(boardManager.getBoardSize() / 2 - 1);
        }
    }

    private void addButtons() {
        for (int i = 0; i < boardManager.getBoardSize(); i++) {
            for (int j = 0; j < boardManager.getBoardSize(); j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 36));
                button.addActionListener(e -> onClick(button));

                buttons[i][j] = button;
                add(button);
            }
        }

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(screenManager);

        JButton restartButton = new JButton("Reiniciar");
        restartButton.addActionListener(e -> restartGame());

        addBottomButtons(restartButton, backButton);
    }

    private void addWhiteSpaces(int quantity) {
        for (int i = 0; i < quantity; i++) {
            add(new JLabel(""));
        }
    }

    private void addBottomButtons(JButton restartButton, JButton backButton) {
        addWhiteSpaces(boardManager.getBoardSize() / 2 - 1);

        add(backButton);
        add(restartButton);

        if (boardManager.getBoardSize() % 2 != 0) {
            addWhiteSpaces(boardManager.getBoardSize() / 2);
        } else {
            addWhiteSpaces(boardManager.getBoardSize() / 2 - 1);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        gameManager.stopCounting();
    }

    private void onClick(JButton buttonClicked) {
        var firstButtonPosition = boardManager.getBoardPosition(buttonClicked.getText());

        if (firstButtonPosition == null) return;

        boolean isValidMove = boardManager.changeButtonsPositions(firstButtonPosition.getXPosition(), firstButtonPosition.getYPosition());

        if (isValidMove) {
            updateBoard();
            gameManager.addClick();
            clickQuantityLabel.setText("Tentativas: " + gameManager.getClickQuantity());
        }

        boolean isOrdered = boardManager.isOrdered();
        if (isOrdered) {
            gameFinished();
        }
    }

    private void gameFinished() {
        gameManager.stopCounting();
        screenManager.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Win"));
    }

    private void updateBoard() {
        for (int i = 0; i < boardManager.getBoardSize(); i++) {
            for (int j = 0; j < boardManager.getBoardSize(); j++) {
                JButton button = buttons[i][j];
                int value = boardManager.getBoardValue(i, j);
                button.setText(value == 0 ? "" : String.valueOf(value));
            }
        }
    }

    private void updateUI() {
        updateBoard();
        clickQuantityLabel.setText("Tentativas: " + gameManager.getClickQuantity());
        timeLabel.setText("Tempo: " + gameManager.getSeconds() + " segs");
    }

    private void restartGame() {
        gameManager.restartGame();
        updateUI();
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
