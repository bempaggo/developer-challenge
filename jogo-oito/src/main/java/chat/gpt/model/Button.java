package chat.gpt.model;

import javax.swing.JButton;

public abstract class Button extends JButton {
    
    public Button() {
        super();
        setFocusable(false);
        setFocusPainted(false);
    }

    public Button(String buttonName) {
        this();
        setText(buttonName);
    }
}
