package chat.gpt.view;

import chat.gpt.util.Fonts;


public class ResetGameButton extends Button {

    private static final String restartButtonName = "Reiniciar";

    public ResetGameButton() {
        super(restartButtonName);
        this.setFont(Fonts.RESTART_BUTTON_FONT.getFont());
    }

}