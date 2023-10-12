package game;

import game.builder.JogoOitoBuilder;

public class App {

    public static void main(String[] args) {
    	new JogoOitoBuilder()
        	.createButtons()
        	.configMenu()
        	.configureInterface();
    }
}
