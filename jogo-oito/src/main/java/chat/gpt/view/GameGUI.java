package chat.gpt.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chat.gpt.controller.GameController;
import chat.gpt.util.Constants;
import chat.gpt.util.Fonts;

public class GameGUI extends JFrame {

    private GameController controller;
    private List<Button> buttons = new ArrayList<>();

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
        buttons = IntStream.range(0, Constants.GRID_SIZE.getMeasure())
                .mapToObj(i -> new Button().withText("").withFont(Fonts.DEFAULT_FONT.getFont()))
                .collect(Collectors.toList());
        buttons.forEach(this::add);
    }

    private void generateResetButton() {
        add(new JLabel(""));
        Button resetButton = new Button().withText("Reiniciar").withFont(Fonts.RESTART_BUTTON_FONT.getFont());
        resetButton.addActionListener(controller);
        add(resetButton);
        add(new JLabel(""));
    }

}
