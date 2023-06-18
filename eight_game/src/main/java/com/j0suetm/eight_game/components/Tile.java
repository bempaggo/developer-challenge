/**
 * file: components/Tile.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 16, 2023
 */

package com.j0suetm.eight_game.components;

import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class Tile implements Movable {
	private Button button;
	Point2D position;
	private HashMap<KeyCode, TileMovement> movementMap;
	
	public Tile(String text, Point2D size) {
		this.button = new Button(text);
		this.button.setMinSize(size.getX(), size.getY());
		
		this.movementMap = new HashMap<KeyCode, TileMovement>(4);
		this.movementMap.put(KeyCode.LEFT, TileMovement.LEFT);
		this.movementMap.put(KeyCode.RIGHT, TileMovement.RIGHT);
		this.movementMap.put(KeyCode.UP, TileMovement.UP);
		this.movementMap.put(KeyCode.DOWN, TileMovement.DOWN);
	}
	
	@Override
	public void moveTo(TileMovement direction) {
		this.position = this.position.add(direction.getDirection());
		this.updateGridConstraints();
	}
	
	@Override
	public void moveToPosition(Point2D newPosition) {
		this.position = newPosition;
		this.updateGridConstraints();
	}
	
	// A posicao é relativa à posição na Array de Tiles
	// na Board, que é convertida aqui em em coluna e linha.
	@Override
	public void moveToRelativePosition(int relativePosition) {
		this.position = new Point2D((relativePosition + 3) % 3, relativePosition / 3);
		this.updateGridConstraints();
	}
	
	private void updateGridConstraints() {
		GridPane.setConstraints(this.button, (int)this.position.getX(), (int)this.position.getY());		
	}
	
	public Button getButton() {
		return this.button;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public HashMap<KeyCode, TileMovement> getMovementMap() {
		return this.movementMap;
	}
}
