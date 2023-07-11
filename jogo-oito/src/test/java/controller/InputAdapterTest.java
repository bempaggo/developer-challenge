package controller;

import view.Button;
import model.MovementInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

class InputAdapterTest {

    private InputAdapter inputAdapter;

    @Mock
    private MovementInterface moveRuleset;

    @Mock
    private ControllerInterface controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inputAdapter = new InputAdapter();
        inputAdapter.setMovementInterface(moveRuleset);
        inputAdapter.setControllerInterface(controller);
        inputAdapter.configurate();
    }

    @Test
    void testActionPerformed() {
        ActionEvent e = mock(ActionEvent.class);
        when(e.getSource()).thenReturn(new Button().withText("1"));
        when(e.getActionCommand()).thenReturn("1");

        inputAdapter.actionPerformed(e);

        verify(moveRuleset).move(1);
        verify(controller).notifyMove();
    }

    @Test
    void testKeyPressedUp() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveUp();
        verify(controller).notifyMove();
    }

    @Test
    void testKeyPressedDown() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveDown();
        verify(controller).notifyMove();
    }

    @Test
    void testKeyPressedLeft() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveLeft();
        verify(controller).notifyMove();
    }

    @Test
    void testKeyPressedRight() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveRight();
        verify(controller).notifyMove();
    }
}
