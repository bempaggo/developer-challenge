package chat.gpt.view;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import chat.gpt.controller.GameController;
import chat.gpt.model.Button;

public class GameGUI extends JFrame {

    public GameController controller;
    public List<Button> buttons = new ArrayList<>();
    private KeyListener keyboardAdapter;

    public GameGUI(GameController controller, KeyListener keyboardAdapter) {
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
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
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

