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
        super.addActionListener(listener);
        return this;
    }

    public ButtonBuilder withText(String text) {
        this.setText(text);
        return this;
    }

    public ButtonBuilder withFont(Font font) {
        super.setFont(font);
        return this;
    }

    @Override
    public void setText(String text) {
        super.setText("0".equals(text) ? "" : text);
    }
}
