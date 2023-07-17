package util;

import java.awt.Font;

public enum Fonts {
    NUMBER_BUTTON_FONT("Arial", Font.BOLD, 36),
    TEXTUAL_BUTTON_FONT("Arial", Font.BOLD, 14);

    private final Font font;

    Fonts(String name, int style, int size) {
        this.font = new Font(name, style, size);
    }

    public Font getFont() {
        return font;
    }
}
