package main.java.chat.gpt;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest extends TestCase {
    @Test
    public void testAppInitialization() {
        // Assert
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }


}