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
import java.util.EnumMap;
import java.util.Map;

import model.MovementInterface;
import util.Keyboard;
import view.Button;

public class InputController extends KeyAdapter implements ActionListener {

    private MovementInterface moveRuleset;
    private ControllerInterface controller;
    private Map<Keyboard, Runnable> keyToActionMap = new EnumMap<>(Keyboard.class);

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

    public void setControllerInterface(ControllerInterface controller) {
        this.controller = controller;
    }    

    private void initializeKeyToActionMap() {
        keyToActionMap.put(Keyboard.UP, moveRuleset::moveUp);
        keyToActionMap.put(Keyboard.DOWN, moveRuleset::moveDown);
        keyToActionMap.put(Keyboard.LEFT, moveRuleset::moveLeft);
        keyToActionMap.put(Keyboard.RIGHT, moveRuleset::moveRight);
    }

    private void processInput(int keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        Runnable action = keyToActionMap.getOrDefault(key, DO_NOTHING);
        action.run();
        controller.notifyMove();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processInput(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveRuleset.move(Integer.parseInt(((Button) e.getSource()).getText()));
        controller.notifyMove();
    }

}
