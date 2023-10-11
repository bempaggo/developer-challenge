package game.builder;

import game.view.JogoOito;

public class JogoOitoBuilder {

	private JogoOito jogoOito;

    public JogoOitoBuilder() {
        this.jogoOito = new JogoOito();
    }

    public JogoOitoBuilder createButtons() {
        this.jogoOito.createButtons();
        return this;
    }

    public JogoOitoBuilder configMenu() {
        this.jogoOito.configMenu();
        return this;
    }

    public JogoOitoBuilder configureInterface() {
        this.jogoOito.configureInterface();
        return this;
    }

    public JogoOito build() {
        return this.jogoOito;
    }
	
}
