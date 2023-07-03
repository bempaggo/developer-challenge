package chat.gpt.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static chat.gpt.util.Constants.*;

import java.awt.GridLayout;

import chat.gpt.controller.GameService;
import chat.gpt.model.ButtonPiece;
import chat.gpt.model.Game;
import chat.gpt.model.ResetGameButton;

public class GameGUI extends JFrame {

    private Game game;
    private GameService service;
    private ButtonPiece[][] buttons = new ButtonPiece[3][3];

    public GameGUI(Game game, GameService service) {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        this.game = game;
        this.service = service;
        generateButtons();
        generateResetButton();
        windowSetUp();

        setVisible(true);
        requestFocus();
    }

    private void windowSetUp() {
        addKeyListener(service);
        setFocusable(true);
        updateGrid();
    }

    public void updateGrid() {
        int[][] grid = game.gridActualState();

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j] == EMPTY) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(String.valueOf(grid[i][j]));
                }
            }
        }
    }

    private void generateButtons() {
        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                ButtonPiece button = new ButtonPiece();
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private void generateResetButton() {
        add(new JLabel(""));
        ResetGameButton resetButton = createResetButton();
        resetButton.addActionListener(service);
        add(resetButton);
        add(new JLabel(""));
    }

    private ResetGameButton createResetButton() {
        return new ResetGameButton();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}