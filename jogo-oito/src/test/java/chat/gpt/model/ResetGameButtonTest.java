package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chat.gpt.controller.ResetGameButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ResetGameButtonTest {

    private boolean ResetGameButtonWasCalled = false;


    @Test
    void testConstructor() {
        ResetGameButtonListener listener = () -> {
            // mock implement
        };

        ResetGameButton resetGameButton = new ResetGameButton(listener);

        Assertions.assertEquals("Reiniciar", resetGameButton.getText());
        Assertions.assertNotNull(resetGameButton.getActionListeners());
        Assertions.assertEquals(1, resetGameButton.getActionListeners().length);
        Assertions.assertSame(resetGameButton, resetGameButton.getActionListeners()[0]);
    }

    @Test
    void testActionPerformed() {
        ResetGameButtonListener listener = new ResetGameButtonListener() {
            @Override
            public void resetGame() {
                ResetGameButtonWasCalled = true;
            }
        };

        ResetGameButton ResetGameButton = new ResetGameButton(listener);

        ActionListener[] actionListeners = ResetGameButton.getActionListeners();
        Assertions.assertEquals(1, actionListeners.length);

        ActionEvent mockEvent = new ActionEvent(ResetGameButton, ActionEvent.ACTION_PERFORMED, "mockCommand");
        actionListeners[0].actionPerformed(mockEvent);

        Assertions.assertTrue(ResetGameButtonWasCalled);
    }
}
