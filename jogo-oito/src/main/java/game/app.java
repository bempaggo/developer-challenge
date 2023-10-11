package game;

import game.builder.JogoOitoBuilder;

public class app {

    public static void main(String[] args) {
    	new JogoOitoBuilder()
        	.createButtons()
        	.configMenu()
        	.configureInterface()
        	.build();
    }
}
