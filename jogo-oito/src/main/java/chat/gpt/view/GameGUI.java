package chat.gpt.view;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.KeyListener;

import chat.gpt.controller.ControllerInterface;
import chat.gpt.util.GridConstants;

public class GameGUI extends JFrame {

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;
    private static final int GRID_COLUMNS = GridConstants.GRID_WIDTH.getMeasure();
    private static final int GRID_ROWS = GridConstants.GRID_WIDTH.getMeasure() + 1;

    public ControllerInterface controller;
    private KeyListener keyboardAdapter;

    public GameGUI(ControllerInterface controller, KeyListener keyboardAdapter) {
        super("Jogo dos Oito");
        this.controller = controller;
        this.keyboardAdapter = keyboardAdapter;
        setupWindow();
        setupKeyListener();
        initializeComponents();
        showWindow();
    }

    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS));
        setLocationRelativeTo(null);
    }

    private void setupKeyListener() {
        addKeyListener(keyboardAdapter);
        setFocusable(true);
    }

    private void initializeComponents() {
        controller.generateButtons(this);
        controller.generateResetButton(this);
        controller.updateGrid(this);
    }

    private void showWindow() {
        setVisible(true);
        requestFocus();
    }
}
