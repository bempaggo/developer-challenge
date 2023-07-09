package chat.gpt.controller;

import javax.swing.JFrame;

public interface ControllerInterface {

    void notifyMove();

    void resetGame();

    void generateResetButton(JFrame view);

    void generateButtons(JFrame view);

    void updateGrid();

}