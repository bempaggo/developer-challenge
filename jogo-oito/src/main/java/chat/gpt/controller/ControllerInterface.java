package chat.gpt.controller;

import javax.swing.JFrame;

public interface ControllerInterface {

    void setView(JFrame view);

    void notifyMove();

    void resetGame();

    void generateResetButton(JFrame view);

    void generateButtons(JFrame view);

    void updateGrid(JFrame view);

}