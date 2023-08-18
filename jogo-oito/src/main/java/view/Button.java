package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button() {
        super();
    }

    public Button(String text) {
        super(text);
    }

    public Button withText(String text) {
        this.setText(text);
        return this;
    }

    public Button withFont(Font font) {
        super.setFont(font);
        return this;
    }

    public Button withActionListener(ActionListener listener) {
        super.addActionListener(listener);
        return this;
    }

    @Override
    public void setText(String text) {
        super.setText(text.equals("0") ? "" : text);
    }
}
