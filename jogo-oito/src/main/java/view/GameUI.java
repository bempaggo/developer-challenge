package view;

import javax.swing.JFrame;

import controller.ControllerInterface;
import util.Fonts;
import util.BoardConstants;
import util.WindowSize;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameUI extends JFrame {

    public ControllerInterface controller;
    private KeyListener keyboardAdapter;
    private ActionListener buttonListener;
    private List<Button> buttons = new ArrayList<>();

    public GameUI() {
        super("Jogo dos Oito");
    }

    public void configurate() {
        setupWindow();
        setupKeyListener();
        initializeComponents();
        showWindow();
    }

    public void setControllerInterface(ControllerInterface controller) {
        this.controller = controller;
    }

    public void setKeyboardAdapter(KeyListener keyboardAdapter) {
        this.keyboardAdapter = keyboardAdapter;
    }

    public void setButtonListener(ActionListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void updateBoard() {
        List<Integer> gridData = controller.boardData();

        buttons.replaceAll(button -> {
            Integer value = gridData.get(buttons.indexOf(button));
            button.setText(value == 0 ? "" : String.valueOf(value));
            return button;
        });
    }

    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WindowSize.WIDTH.getMeasure(),
                WindowSize.HEIGHT.getMeasure());
        setLayout(new GridLayout(BoardConstants.WIDTH.getMeasure() + 1,
                BoardConstants.WIDTH.getMeasure()));
        setLocationRelativeTo(null);
    }

    private void setupKeyListener() {
        addKeyListener(keyboardAdapter);
        setFocusable(true);
    }

    private void initializeComponents() {
        generateButtons();
        generateBoardSolutionButton();
        generateResetButton();
        updateBoard();
    }

    private void showWindow() {
        setVisible(true);
        requestFocus();
    }

    private void generateBoardSolutionButton() {
        Button boardSolutionButton = new Button()
                .withText("Gabarito")
                .withFont(Fonts.TEXTUAL_BUTTON_FONT.getFont())
                .withActionListener(e -> controller.boardSolution());
        add(boardSolutionButton);
    }

    private void generateResetButton() {
        Button resetButton = new Button()
                .withText("Reiniciar")
                .withFont(Fonts.TEXTUAL_BUTTON_FONT.getFont())
                .withActionListener(e -> controller.resetGame());
        add(resetButton);
    }

    private void generateButtons() {
        List<Integer> gridData = controller.boardData();

        buttons = IntStream.range(0, BoardConstants.SIZE.getMeasure())
                .mapToObj(i -> {
                    int value = gridData.get(i);
                    Button button = new Button()
                            .withText(value == 0 ? "" : String.valueOf(value))
                            .withFont(Fonts.NUMBER_BUTTON_FONT.getFont())
                            .withActionListener(buttonListener);
                    return button;
                })
                .collect(Collectors.toList());

        buttons.forEach(this::add);
    }

}
