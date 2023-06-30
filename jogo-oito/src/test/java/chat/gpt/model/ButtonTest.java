package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Font;

public class ButtonTest {

    @Test
    void testGenerateButtonWithText() {
        String text = "Teste";
        ButtonPiece button = ButtonPiece.generateButton(text);
        Assertions.assertEquals(text, button.getText());
    }

    @Test
    void testGenerateEmptyButton() {
        ButtonPiece button = ButtonPiece.generateEmptyButton();
        Assertions.assertEquals("", button.getText());
        Assertions.assertEquals(new Font("Arial", Font.BOLD, 36), button.getFont());
    }

}
