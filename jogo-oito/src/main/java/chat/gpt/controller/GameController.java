package chat.gpt.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JLabel;

import chat.gpt.model.Button;
import chat.gpt.model.GridInterface;
import chat.gpt.util.Fonts;
import chat.gpt.util.GridConstants;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.GameGUI;

public class GameController implements ButtonActionListener {

    private GameGUI view;
    private GridInterface grid;

    public GameController(GameGUI view, GridInterface grid) {
        this.view = view;
        this.grid = grid;
    }

    public void setView(GameGUI view) {
        this.view = view;
    }

    public void notifyMove() {
        view.controller.updateGrid(view);
        if (gameIsComplete()) {
            MessagePopUp.showMessage("Parabéns, você venceu!");
        }
    }

    @Override
    public void resetGame() {
        grid.reset();
        view.controller.updateGrid(view);
    }

    public boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

    public void generateResetButton(GameGUI gameGUI) {
        gameGUI.add(new JLabel(""));
        Button resetButton = new Button()
                .withText("Reiniciar")
                .withFont(Fonts.RESTART_BUTTON_FONT.getFont());
        resetButton.addActionListener(this);
        gameGUI.add(resetButton);
        gameGUI.add(new JLabel(""));
    }

    public void generateButtons(GameGUI gameGUI) {
        gameGUI.buttons = IntStream.range(0, GridConstants.GRID_SIZE.getMeasure() )
                .mapToObj(i -> new Button()
                        .withText("")
                        .withFont(Fonts.DEFAULT_FONT.getFont()))
                .collect(Collectors.toList());
        gameGUI.buttons.forEach(gameGUI::add);
    }

    public void updateGrid(GameGUI gameGUI) {
        List<Integer> gridData = grid.getGridData();
    
        gameGUI.buttons.replaceAll(button -> {
            int value = gridData.get(gameGUI.buttons.indexOf(button));
            button.setText(value == 0 ? "" : String.valueOf(value));
            return button;
        });
    }

}