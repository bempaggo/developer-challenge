package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import javax.swing.JButton;

public abstract class Button extends JButton {
    
    protected Button() {
        super();
        setFocusable(false);
        setFocusPainted(false);
    }

    protected Button(String buttonName) {
        this();
        setText(buttonName);
    }
}
