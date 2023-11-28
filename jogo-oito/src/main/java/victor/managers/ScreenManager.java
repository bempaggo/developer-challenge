package victor.managers;

import javax.swing.*;

import victor.screens.Game;
import victor.screens.Welcome;
import victor.screens.Winning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ScreenManager implements ActionListener {
    private GameManager gameManager;
    private final HashMap<String, JFrame> screens = new HashMap<>();

    public ScreenManager() {
        screens.put("Welcome", new Welcome(this));
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        JFrame welcome = screens.get("Welcome");

        switch (buttonText) {
            case "Começar" -> {
                welcome.setVisible(false);
                int boardSize = ((Welcome) welcome).getOptionSelected();

                this.gameManager = new GameManager(boardSize);
                screens.put("PlayGame", new Game(this));
            }
            case "Voltar" -> {
                screens.get("PlayGame").dispose();
                welcome.setVisible(true);
            }
            case "Win" -> {
                screens.get("PlayGame").setEnabled(false);
                screens.put("Win", new Winning(this));
            }
            case "Tela inicial" -> {
                screens.get("PlayGame").dispose();
                screens.get("Win").dispose();
                welcome.setVisible(true);
            }
            default -> System.out.println("Default");
        }
    }
}
