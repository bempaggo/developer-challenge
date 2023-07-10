package chat.gpt.controller;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public interface ControllerInterface {

    void notifyMove();

    void resetGame();

    void generateResetButton(JFrame view, ActionListener resetButtonListener);

    void generateButtons(JFrame view, ActionListener buttonListener);

    void updateGrid();

}