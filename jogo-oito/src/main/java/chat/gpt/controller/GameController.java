package chat.gpt.controller;

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

public class GameController implements ButtonActionListener, ControllerInterface {

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

    @Override
    public void resetGame() {
        grid.reset();
        updateGrid();
    }

    @Override
    public void generateResetButton(JFrame view) {
        view.add(new JLabel(""));
        Button resetButton = new Button()
                .withText("Reiniciar")
                .withFont(Fonts.RESTART_BUTTON_FONT.getFont());
        resetButton.addActionListener(this);
        view.add(resetButton);
        view.add(new JLabel(""));
    }

    @Override
    public void generateButtons(JFrame view) {
        buttons = IntStream.range(0, GridConstants.GRID_SIZE.getMeasure() )
                .mapToObj(i -> new Button()
                        .withText("")
                        .withFont(Fonts.DEFAULT_FONT.getFont()))
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