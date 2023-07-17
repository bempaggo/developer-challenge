/*  Essa classe é um controlador intermediário entre a UI, as regras de movimentação e o controlador do jogo;
Ela mapeia as entradas do usuário para as ações correspondentes e as encaminha para a lógica do jogo.
Especificamente, ela captura eventos do teclado e ações dos botões e os mapeia para as ações de movimentação
e notifica o Controller do jogo sobre uma possível mudança de estado do jogo.
*/
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import model.MovementInterface;
import view.Button;

public class InputController extends KeyAdapter implements ActionListener {

    private MovementInterface moveRuleset;
    private Map<Integer, Runnable> keyToActionMap = new HashMap<>();

    private static final Runnable DO_NOTHING = () -> {
    };

    public InputController() {
    }

    public void configurate() {
        initializeKeyToActionMap();
    }

    public void setMovementInterface(MovementInterface moveRuleset) {
        this.moveRuleset = moveRuleset;
    }   

    private void initializeKeyToActionMap() {
        keyToActionMap.put(KeyEvent.VK_UP, moveRuleset::moveUp);
        keyToActionMap.put(KeyEvent.VK_DOWN, moveRuleset::moveDown);
        keyToActionMap.put(KeyEvent.VK_LEFT, moveRuleset::moveLeft);
        keyToActionMap.put(KeyEvent.VK_RIGHT, moveRuleset::moveRight);
    }

    private void processInput(int keyCode) {
        Runnable action = keyToActionMap.getOrDefault(keyCode, DO_NOTHING);
        action.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processInput(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveRuleset.move(Integer.parseInt(((Button) e.getSource()).getText()));
    }

}
