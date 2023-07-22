package builder;

import view.JogoDosOito;

public class JogoDosOitoBuilder {

    private JogoDosOito jogoDosOito;

    public JogoDosOitoBuilder() {
        this.jogoDosOito = new JogoDosOito();
    }

    public JogoDosOitoBuilder createButtons() {
        this.jogoDosOito.createButtons();
        return this;
    }

    public JogoDosOitoBuilder configureMenu() {
        this.jogoDosOito.configureMenu();
        return this;
    }

    public JogoDosOitoBuilder configureInterface() {
        this.jogoDosOito.configureInterface();
        return this;
    }

    public JogoDosOito build() {
        return this.jogoDosOito;
    }
}
