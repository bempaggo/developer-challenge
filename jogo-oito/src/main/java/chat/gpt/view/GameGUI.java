package chat.gpt.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static chat.gpt.util.Constants.*;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chat.gpt.controller.GameController;

public class GameGUI extends JFrame {

    private GameController controller;
    private List<ButtonPiece> buttons = new ArrayList<>();

    public GameGUI( GameController controller) {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        
        this.controller = controller;
        generateButtons();
        generateResetButton();
        windowSetUp();

        setVisible(true);
        requestFocus();
    }

    private void windowSetUp() {
        addKeyListener(controller);
        setFocusable(true);
        updateGrid();
    }

    public void updateGrid() {
        List<Integer> gridData = controller.gridActualState();

        buttons.replaceAll(button -> {
            int value = gridData.get(buttons.indexOf(button));
            button.setText(value == 0 ? "" : String.valueOf(value));
            return button;
        });
    }

    private void generateButtons() {
        buttons = IntStream.range(0, GRID_AREA)
        .mapToObj(i -> new ButtonPiece())
        .peek(this::add)
        .collect(Collectors.toList());
    }

    private void generateResetButton() {
        add(new JLabel(""));
        ResetGameButton resetButton = new ResetGameButton();
        resetButton.addActionListener(controller);
        add(resetButton);
        add(new JLabel(""));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
