package chat.gpt.view;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import chat.gpt.controller.ControllerInterface;
import chat.gpt.util.GridConstants;
import chat.gpt.util.WindowSize;

public class GameGUI extends JFrame {

    public ControllerInterface controller;
    private KeyListener keyboardAdapter;
    private ActionListener buttonListener;

    public GameGUI(ControllerInterface controller, KeyListener inputAdapter, ActionListener buttonListener) {
        super("Jogo dos Oito");
        this.controller = controller;
        this.keyboardAdapter = inputAdapter;
        this.buttonListener = buttonListener;
        setupWindow();
        setupKeyListener();
        initializeComponents();
        showWindow();
    }

    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WindowSize.WINDOW_WIDTH.getMeasure(), 
                WindowSize.WINDOW_WIDTH.getMeasure());
        setLayout(new GridLayout(GridConstants.GRID_WIDTH.getMeasure() + 1,
                GridConstants.GRID_WIDTH.getMeasure()));
        setLocationRelativeTo(null);
    }

    private void setupKeyListener() {
        addKeyListener(keyboardAdapter);
        setFocusable(true);
    }

    private void initializeComponents() {
        controller.generateButtons(this, buttonListener);
        controller.generateResetButton(this, buttonListener);
        controller.updateGrid();
    }

    private void showWindow() {
        setVisible(true);
        requestFocus();
    }
}
