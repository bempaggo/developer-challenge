package view;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

public class ButtonBuilder extends JButton {

    public ButtonBuilder() {
        super();
        setFocusable(false);
        setFocusPainted(false);
    }

    public ButtonBuilder withActionListener(ActionListener listener) {
        addActionListener(listener);
        return this;
    }

    public ButtonBuilder withText(String text) {
        setText("0".equals(text) ? "" : text);
        return this;
    }

    public ButtonBuilder withFont(Font font) {
        setFont(font);
        return this;
    }
}
