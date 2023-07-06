package chat.gpt.view;

import chat.gpt.util.Fonts;

public class ButtonPiece extends Button {

    public ButtonPiece() {
        super();
        setFont(Fonts.DEFAULT_FONT.getFont()); 
    }

    public ButtonPiece(String buttonName) {
        this();
        setText(buttonName);
    }

}
