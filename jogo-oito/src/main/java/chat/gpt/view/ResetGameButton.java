package chat.gpt.view;

import static chat.gpt.util.Constants.RESTART_BUTTON_FONT;


public class ResetGameButton extends Button {

    private static final String restartButtonName = "Reiniciar";

    public ResetGameButton() {
        super(restartButtonName);
        this.setFont(RESTART_BUTTON_FONT);
    }

}