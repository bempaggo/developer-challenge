package chat.gpt.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

import chat.gpt.model.GridInterface;
import chat.gpt.util.Fonts;
import chat.gpt.util.GridConstants;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.Button;
import chat.gpt.view.ResetButton;

public class GameController implements ControllerInterface {

    private GridInterface grid;
    private List<Button> buttons = new ArrayList<>();

    public GameController(GridInterface grid) {
        this.grid = grid;
    }

    @Override
    public void notifyMove() {
        updateGrid();
        if (gameIsComplete()) {
            MessagePopUp.showMessage("Parabéns, você venceu!");
        }
    }

    public void resetGame() {
        grid.reset();
        updateGrid();
    }

    @Override
    public void generateResetButton(JFrame view, ActionListener resetButtonListener) {
        view.add(new JLabel(""));
        Button resetButton = new ResetButton()
                .withActionListener(resetButtonListener);
        view.add(resetButton);
        view.add(new JLabel(""));
    }

    @Override
    public void generateButtons(JFrame view, ActionListener buttonListener) {
        List<Integer> gridData = grid.getGridData();

        buttons = IntStream.range(0, GridConstants.GRID_SIZE.getMeasure())
                .mapToObj(i -> {
                    int value = gridData.get(i);
                    Button button = new Button()
                            .withText(value == 0 ? "" : String.valueOf(value))
                            .withFont(Fonts.DEFAULT_FONT.getFont())
                            .withActionListener(buttonListener);
                    return button;
                })
                .collect(Collectors.toList());

        buttons.forEach(view::add);
    }

    @Override
    public void updateGrid() {
        List<Integer> gridData = grid.getGridData();

        buttons.replaceAll(button -> {
            int value = gridData.get(buttons.indexOf(button));
            button.setText(value == 0 ? "" : String.valueOf(value));
            return button;
        });
    }

    private boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

}