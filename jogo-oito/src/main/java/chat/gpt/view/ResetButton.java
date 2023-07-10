package chat.gpt.view;

import chat.gpt.util.Fonts;

public class ResetButton extends Button {

    public ResetButton() {
        super();
        withText("Reiniciar")
        .withFont(Fonts.RESTART_BUTTON_FONT.getFont());
    }
    
}
