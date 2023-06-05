package chat.gpt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chat.gpt.domain.actions.MoveAction;
import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.listeners.KeyboardListener;

public class KeyboardListenerTest {
    @Test
    @DisplayName("Test Keypressed, shoulds notify all object when pressed.")
    void keyPressed_Shoulds_Notify() {
        // Arrange
        KeyboardListener listener = new KeyboardListener();

        MoveAction actionMock1 = mock(MoveAction.class);
        MoveAction actionMock2 = mock(MoveAction.class);

        listener.subscribe(0, new HashSet<ActionEventDelegate<Integer>>() {
            {
                add(actionMock1);
            }
        });
        listener.subscribe(0, actionMock2);

        KeyEvent keyMock = mock(KeyEvent.class);
        when(keyMock.getKeyCode()).thenReturn(0);

        // Act
        listener.keyPressed(keyMock);

        // Asserts
        verify(keyMock, atLeastOnce()).getKeyCode();
        verify(actionMock1, times(1)).doAction(anyInt());
        verify(actionMock2, times(1)).doAction(anyInt());
    }
}
