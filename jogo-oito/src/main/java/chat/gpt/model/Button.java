package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import javax.swing.JButton;

public class Button extends JButton {

    private Button() {
        super();
        setFont(DEFAULT_FONT); 
    }

    protected Button(String buttonName) {
        this();
        setText(buttonName);
    }

    public static Button generateButton(String buttonName) {
        return new Button(buttonName);
    }

    public static Button generateEmptyButton() {
        return new Button();
    }
}
