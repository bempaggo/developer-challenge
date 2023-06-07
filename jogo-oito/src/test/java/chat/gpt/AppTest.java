package chat.gpt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    @Test
    public void testAppInitialization() {
        // Assert
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}

