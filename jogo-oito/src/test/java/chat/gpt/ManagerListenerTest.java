package chat.gpt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import chat.gpt.domain.interfaces.EventListener;
import chat.gpt.domain.listeners.KeyboardListener;
import chat.gpt.domain.listeners.ManagerListener;
import chat.gpt.domain.listeners.MouseListener;
import chat.gpt.domain.listeners.NotificationListener;

public class ManagerListenerTest {

    @Test
    void Manager_getListenerTest() {
        // Arrange
        ManagerListener manager = new ManagerListener();

        // Asserts
        assertInstanceOf(KeyboardListener.class, manager.getListener(KeyboardListener.class));
        assertInstanceOf(MouseListener.class, manager.getListener(MouseListener.class));
        assertInstanceOf(NotificationListener.class, manager.getListener(NotificationListener.class));
        assertEquals(null, manager.getListener(EventListener.class));
    }

    @Test
    void Manager_testEnableAll() {
        // Arrange
        ManagerListener manager = new ManagerListener();

        // Asserts
        manager.setEnabledEvents(true);
        assertEquals(manager.getListener(KeyboardListener.class).getEnabled(), true);
        assertEquals(manager.getListener(MouseListener.class).getEnabled(), true);
        assertEquals(manager.getListener(NotificationListener.class).getEnabled(), true);

        manager.setEnabledEvents(false);
        assertEquals(manager.getListener(KeyboardListener.class).getEnabled(), false);
        assertEquals(manager.getListener(MouseListener.class).getEnabled(), false);
        assertEquals(manager.getListener(NotificationListener.class).getEnabled(), false);
    }

}
