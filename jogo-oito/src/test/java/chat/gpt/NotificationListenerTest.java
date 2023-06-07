package chat.gpt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.listeners.NotificationListener;

public class NotificationListenerTest {

    private class MockedAction implements ActionEventDelegate<String> {
        @Override
        public void doAction(String dataEvent) {
            System.out.println(dataEvent);
        }
    };

    @Test
    void Notification_Listener_Shoulds_Notify() {
        // Arrange
        NotificationListener listener = new NotificationListener();

        var actionMock1 = mock(MockedAction.class);
        var actionMock2 = mock(MockedAction.class);

        listener.subscribe("notification1", new HashSet<ActionEventDelegate<String>>() {
            {
                add(actionMock1);
            }
        }).subscribe("notification2", actionMock2);

        // // Act
        listener.notify("notification1", "notify1");
        listener.notify("notification2", "notify2");

        // Asserts
        verify(actionMock1, times(1)).doAction(any());
        verify(actionMock2, times(1)).doAction(any());
    }
}
