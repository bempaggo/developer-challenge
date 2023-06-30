package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Font;

public class ButtonTest {

    @Test
    void testGenerateButtonWithText() {
        String text = "Teste";
        Button button = Button.generateButton(text);
        Assertions.assertEquals(text, button.getText());
    }

    @Test
    void testGenerateEmptyButton() {
        Button button = Button.generateEmptyButton();
        Assertions.assertEquals("", button.getText());
        Assertions.assertEquals(new Font("Arial", Font.BOLD, 36), button.getFont());
    }

}
