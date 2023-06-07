package chat.gpt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import chat.gpt.domain.actions.MouseMoveAction;
import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.listeners.MouseListener;
import chat.gpt.domain.table.TableCell;

public class MouseListenerTest {
    @Test
    void Mouse_getListenerTest() {
        // Arrange
        MouseListener listener = new MouseListener();

        MouseMoveAction actionMock1 = mock(MouseMoveAction.class);
        MouseMoveAction actionMock2 = mock(MouseMoveAction.class);

        
        listener.subscribe("click", new HashSet<ActionEventDelegate<TableCell>>() {
            {
                add(actionMock1);
            }
        }).subscribe("click2", actionMock2);

        
        // Act
        listener.notify("click", new TableCell());
        listener.notify("click2", new TableCell());

        // Asserts
        verify(actionMock1, times (1)).doAction(any());
        verify(actionMock2, times(1)).doAction(any());
    }
}
