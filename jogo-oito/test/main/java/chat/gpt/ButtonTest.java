package main.java.chat.gpt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JButton;

public class ButtonTest {

    private Button button;
    private JButton jButton;

    @BeforeEach
    public void setup() {
        jButton = new JButton();
        button = new Button(jButton, 10);
    }

    @Test
    public void testGetjButton() {
        JButton result = button.getjButton();
        Assertions.assertEquals(jButton, result, "O botão retornado não é igual ao botão original.");
    }

    @Test
    public void testGetValue() {
        Integer result = button.getValue();
        Assertions.assertEquals(10, result, "O valor retornado não é igual ao valor original.");
    }

    @Test
    public void testSetValue() {
        button.setValue(20);
        Integer result = button.getValue();
        Assertions.assertEquals(20, result, "O valor não foi alterado corretamente.");
    }

}
