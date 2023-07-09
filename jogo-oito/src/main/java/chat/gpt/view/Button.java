package chat.gpt.view;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button() {
        super();
        setFocusable(false);
        setFocusPainted(false);
    }

    public Button withActionListener(ActionListener listener) {
        addActionListener(listener);
        return this;
    }

    public Button withText(String text) {
        setText(text);
        return this;
    }

    public Button withFont(Font font) {
        setFont(font);
        return this;
    }
}
