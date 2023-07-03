package chat.gpt;

import config.Config;
import facade.Controller;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Cell;

public class JogoDosOito extends JFrame implements KeyListener {

    private final List<List<JButton>> buttons;
    private final Controller controller;
    private JButton reset;

    public JogoDosOito() {
        super(Config.title);
        this.controller = new Controller(Config.maxRows, Config.maxColumns, Config.seed);
        this.controller.configBoard();
        this.buttons = new ArrayList<>();
    }

    private void config() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Config.maxRows * 100, Config.maxColumns * 100);
        setLayout(new GridLayout(Config.maxRows + 1, Config.maxColumns));
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    private void configReset() {
        this.reset = new JButton("Reiniciar");
        this.reset.addActionListener((ActionEvent e) -> {
            this.resetGame();
            SwingUtilities.getRoot(this.reset).requestFocus();
        });
        int position = Config.maxColumns / 2;
        for (int index = 0; index < position; index++) {
            add(new JLabel(""));
        }

        add(this.reset);

    }

    private void resetGame() {
        this.controller.configBoard();
        this.updateBoard(this.controller.getMatrix());
    }

    private void createButtons() {
        JButton button;
        List<List<Cell>> matrix = this.controller.getMatrix();
        for (List<Cell> rows : matrix) {
            List<JButton> rowButtons = new ArrayList<>();
            for (Cell cell : rows) {
                button = this.configButton(cell);
                add(button);
                rowButtons.add(button);
            }
            this.buttons.add(rowButtons);
        }
    }

    private Integer textToValue(String text) {
        if (text.equals("")) {
            return 0;
        }
        return Integer.valueOf(text);

    }

    private JButton configButton(Cell cell) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 36));
        button.setText(cell.valueToText());
        button.addActionListener((ActionEvent e) -> {
            Integer value = this.textToValue(button.getText());
            Cell cellFound = this.controller.findCellByValue(value);
            if (cellFound.getValidPosition()) {
                Cell emptyCell = this.controller.findEmptyCell();
                this.controller.swapValue(emptyCell, cellFound);
                this.updateBoard(this.controller.getMatrix());
            }
            SwingUtilities.getRoot(button).requestFocus();

        });
        return button;

    }

    private void motionControl(int rowMovement, int columnMovement) {
        List<List<Cell>> matrix = this.controller.swapCells(rowMovement, columnMovement);
        this.updateBoard(matrix);
        if (this.controller.checkGameOver()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            this.resetGame();
        }

    }

    private void updateBoard(List<List<Cell>> matrix) {
        for (int indexRow = 0; indexRow < Config.maxRows; indexRow += 1) {
            for (int indexColumn = 0; indexColumn < Config.maxColumns; indexColumn += 1) {
                JButton button = this.buttons.get(indexRow).get(indexColumn);
                String value = matrix.get(indexRow).get(indexColumn).valueToText();
                button.setText(value);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP ->
                motionControl(1, 0);
            case KeyEvent.VK_DOWN ->
                motionControl(-1, 0);
            case KeyEvent.VK_LEFT ->
                motionControl(0, 1);
            case KeyEvent.VK_RIGHT ->
                motionControl(0, -1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JogoDosOito game = new JogoDosOito();
        game.createButtons();
        game.configReset();
        game.config();

    }

}
