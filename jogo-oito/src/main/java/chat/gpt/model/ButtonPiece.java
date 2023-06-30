package chat.gpt.model;

import static chat.gpt.util.Constants.DEFAULT_FONT;

public class ButtonPiece extends Button {

    private ButtonPiece() {
        super();
        setFont(DEFAULT_FONT); 
    }

    protected ButtonPiece(String buttonName) {
        this();
        setText(buttonName);
    }

    public static ButtonPiece generateButton(String buttonName) {
        return new ButtonPiece(buttonName);
    }

    public static ButtonPiece generateEmptyButton() {
        return new ButtonPiece();
    }
}
