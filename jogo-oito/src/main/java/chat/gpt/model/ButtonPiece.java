package chat.gpt.model;

import static chat.gpt.util.Constants.DEFAULT_FONT;

public class ButtonPiece extends Button {

    public ButtonPiece() {
        super();
        setFont(DEFAULT_FONT); 
    }

    public ButtonPiece(String buttonName) {
        this();
        setText(buttonName);
    }

    public static ButtonPiece generateButton(String buttonName) {
        return new ButtonPiece(buttonName);
    }

}