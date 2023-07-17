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

    private InputController inputAdapter;

    @Mock
    private MovementInterface moveRuleset;

    @Mock
    private ControllerInterface controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inputAdapter = new InputController();
        inputAdapter.setMovementInterface(moveRuleset);
        inputAdapter.configurate();
    }

    @Test
    void testActionPerformed() {
        ActionEvent e = mock(ActionEvent.class);
        when(e.getSource()).thenReturn(new Button().withText("1"));
        when(e.getActionCommand()).thenReturn("1");

        inputAdapter.actionPerformed(e);

        verify(moveRuleset).move(1);
    }

    @Test
    void testKeyPressedUp() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveUp();
    }

    @Test
    void testKeyPressedDown() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveDown();
    }

    @Test
    void testKeyPressedLeft() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveLeft();
    }

    @Test
    void testKeyPressedRight() {
        KeyEvent e = new KeyEvent(new Button().withText("1"), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');

        inputAdapter.keyPressed(e);

        verify(moveRuleset).moveRight();
    }
}
