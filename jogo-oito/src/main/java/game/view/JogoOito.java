package game.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import game.facade.Controller;
import game.interfaces.Vertex;

public class JogoOito extends JFrame implements KeyListener {
	
	private final List<JButton> buttons;
    private final Controller controller;
    private JButton reset;
    private JButton feedback;

    public JogoOito() {
        super("Jogo dos Oito");
        this.controller = new Controller();
        this.buttons = new ArrayList<>();
        addKeyListener(this);
    }

    public void configureInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        setVisible(true);
        setFocusable(true);
    }

    public void createButtons() {
    	List<Vertex> cells = this.controller.getCells();
    	IntStream.range(0, cells.size())
        .forEach(index -> {
        	JButton button = this.configButton(cells.get(index));
        	add(button);
            this.buttons.add(button);
        });	
    }

    private Integer textToValue(String text) {
        return Optional.ofNullable(text)
                .map(Integer::valueOf)
                .orElse(0);
    }

    public JButton configButton(Vertex cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());

        button.addActionListener((ActionEvent e) -> {
            this.controller.click(this.textToValue(button.getText()));
            this.updateBoard();
            this.checkVictory();
            SwingUtilities.getRoot(button).requestFocus();
        });
        return button;
    }

    public void checkVictory() {
        Optional.ofNullable(this.controller.checkVictory())
                .filter(Boolean::booleanValue)
                .ifPresent(victory -> {
                    JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
                    settingGame(false);
                });
    }

    public void configMenu() {
        this.reset = this.configReset();
        this.feedback = this.configFeedback();
        add(this.feedback);
        add(this.reset);
        add(new JLabel(""));
    }

    private JButton configReset() {
        JButton buttonReset = new JButton("Reiniciar");
        buttonReset.addActionListener((ActionEvent e) -> {
            this.settingGame(false);
            SwingUtilities.getRoot(buttonReset).requestFocus();
        });
        return buttonReset;
    }
    
    private JButton configFeedback() {
        JButton buttonFeedback = new JButton("Gabarito");
        buttonFeedback.addActionListener((ActionEvent e) -> {
            this.settingGame(true);
            SwingUtilities.getRoot(buttonFeedback).requestFocus();
        });
        return buttonFeedback;
    }


    private void settingGame(Boolean feedback) {
        this.controller.setting(feedback);
        this.updateBoard();
    }
    
   public void updateBoard() {
        List<Vertex> cells = this.controller.getCells();
        IntStream.range(0, cells.size())
                .forEach(index -> {
                    JButton button = this.buttons.get(index);
                    button.setText(cells.get(index).valueToText());
                });
    }
   
   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyPressed(KeyEvent e) {
       this.controller.swap(e.getKeyCode());
       this.updateBoard();
       this.checkVictory();
   }

   @Override
   public void keyReleased(KeyEvent e) {
   }
	
}
