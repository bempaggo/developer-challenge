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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        this.controller = controller;
        this.keyboardAdapter = keyboardAdapter;
        controller.generateButtons(this);
        controller.generateResetButton(this);
        windowSetUp();

        setVisible(true);
        requestFocus();
    }

    private void windowSetUp() {
        addKeyListener(this.keyboardAdapter);
        setFocusable(true);
        controller.updateGrid(this);
    }

}
