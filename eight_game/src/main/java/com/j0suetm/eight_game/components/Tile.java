/**
 * file: components/Tile.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 16, 2023
 */

package com.j0suetm.eight_game.components;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Tile {
	private Button button;
	Point2D position;
	
	public Tile(String text, Point2D size) {
		this.button = new Button(text);
		this.button.setMinSize(size.getX(), size.getY());
	}
	
	public void moveToPosition(Point2D newPosition) {
		this.position = newPosition;
		GridPane.setConstraints(this.button, (int)newPosition.getX(), (int)newPosition.getY());
	}
	
	// A posicao é relativa à posição na Array de Tiles
	// na Board, que é convertida aqui em em coluna e linha.
	public void moveToRelativePosition(int relativePosition) {
		this.position = new Point2D((relativePosition + 3) % 3, relativePosition / 3);
		GridPane.setConstraints(this.button, (int)this.position.getX(), (int)this.position.getY());
	}
	
	public Button getButton() {
		return this.button;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
}
