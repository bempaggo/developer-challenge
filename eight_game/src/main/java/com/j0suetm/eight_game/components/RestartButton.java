package com.j0suetm.eight_game.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class RestartButton {
	private Button button;
	
	public RestartButton(int width, int height) {
		this.button = new Button("Reiniciar");
		this.button.setMinSize(width, height);
	}
	
	public void addEventHandler(Board board) {
		this.button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					board.shuffleTiles();
				}
			}
		);
	}
	
	public Button getButton() {
		return this.button;
	}
}
