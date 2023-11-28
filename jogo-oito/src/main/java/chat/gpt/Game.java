package main.java.chat.gpt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game extends JFrame {

    private final java.util.List<Button> buttonList;
    Board board;
    private final Rules rules;

    public Game(Rules rules){
        super("Jogo Desafio Bempaggo");
        this.buttonList = new ArrayList<>(9);
        this.rules = rules;
        start();
    }

    private void start(){
        startBoard();
        startFrame();
        createButtons();
        setFocusable(true);
        reloadBoard();
        setVisible(true);
    }
    private void startBoard() {
        board = new Board(initialConf());
    }

    private void startFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(4, 3));
    }
    public void createButtons(){
        for(Integer i = 0; i < 9;i++){
            JButton jbutton = new JButton();
            jbutton.setFont(new Font("Times_New_Roman", Font.BOLD, 40));
            jbutton.addMouseListener(moveButton());
            add(jbutton);
            buttonList.add(new Button(jbutton, i));
        }

        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(e -> shuffleGame());
        add(shuffleButton);

        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> restart());
        add(restart);

        JButton presentation = new JButton("Presentation");
        presentation.addActionListener(e -> presentationFunction());
        add(presentation);
    }

    private MouseAdapter moveButton() {
        JFrame view = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton jButton = (JButton) e.getSource();
                Button clickedButton;
                boolean validMoviment;
                try {
                    clickedButton = getCLickedButton(jButton);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    validMoviment = makeMove(clickedButton.getValue());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                reloadBoard();
                if(validMoviment) {
                    winner(view);
                }
            }
        };
    }

    public Button getCLickedButton(JButton jButton) throws Exception {
        return buttonList.stream().filter(button -> button.getjButton().equals(jButton))
                .findFirst().orElseThrow(() -> new Exception("Botao clicado nao foi encontrado!"));
    }

    public void winner(JFrame view) {
        Boolean winner = rules.winner(board);
        if (winner) {
            JOptionPane.showMessageDialog(view, "Parabéns! Você Venceu!!!!");
        }
    }
    public void shuffleGame() {
        shufflePieces();
        reloadBoard();
    }

    public void restart() {
        startBoard();
        reloadBoard();
    }

    public void presentationFunction(){
        JFrame view = this;
        JOptionPane.showMessageDialog(view, "Olá sou a Caroline e espero que vocês gostem da minha resolução do projeto," +
                " aprendi bastante fazendo e acredito que vou contribuir bastante para a empresa");
    }

    public void reloadBoard() {
        IntStream.range(0, 9).forEach(i -> {
            try {
                buttonList.get(i).getjButton().setText(board.getPieceUsingPosition(i).getStringValue());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
                    try {
                        buttonList.get(i).setValue(board.getPieceUsingPosition(i).getValue());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }


    public java.util.List<Piece> initialConf() {
        java.util.List<Piece> pieceList = new ArrayList<>();
        IntStream.range(0, 9).forEach(i -> pieceList.add(new Piece(i + 1, i)));
        return pieceList;
    }


    public void shufflePieces() {
        java.util.List<Piece> pieces = board.pieceList();
        java.util.List<Integer> positions = IntStream.range(0, 9).boxed().collect(Collectors.toList());

        Collections.shuffle(positions);
        IntStream.range(0, 9).forEach(i -> pieces.get(i).setPosition(positions.get(i)));
    }

    public boolean makeMove(Integer value) throws Exception {
        Piece selectedPiece = board.getPieceUsingValue(value);
        Piece emptyPiece = board.getEmptyPiece();
        boolean validMoviment = rules.isValid(selectedPiece, emptyPiece);

        if (validMoviment) {
            changePieces(selectedPiece, emptyPiece);
        }
        return  validMoviment;
    }

    public void changePieces(Piece selectedPiece, Piece emptyPiece) {
        Integer posAux = selectedPiece.getPosition();
        selectedPiece.setPosition(emptyPiece.getPosition());
        emptyPiece.setPosition(posAux);
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public Board getBoard() {
        return board;
    }

}