/**
 * file: components/Board.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 16, 2023
 */

package com.j0suetm.eight_game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Board {
	private GridPane gridPane;
	private final Point2D gridSize = new Point2D(3, 3);
	private List<Tile> tiles;
	private Point2D size;
	private Point2D blankTile;
	
	public Board(int width, int height, boolean shouldShuffle) {
		this.size = new Point2D(width, height);
		this.gridPane = new GridPane();
		this.gridPane.setMinSize(this.size.getX(), this.size.getY());
		this.gridPane.setAlignment(Pos.TOP_CENTER);
		this.blankTile = new Point2D(2, 2);
		
		this.buildTiles();
		if (shouldShuffle)
			this.shuffleTiles();
	}
	
	private void buildTiles() {
		Point2D defaultTileSize =
				new Point2D(this.size.getX() / this.gridSize.getX(),
										this.size.getY() / this.gridSize.getY());
		
		this.tiles = new ArrayList<Tile>(8);
		// utilizo for somente para ter um número crescente, para dar nome
		// aos `tiles` sem precisar de fazer um por um.
		for (int i = 0; i < 8; ++i) {
			Tile tile = new Tile(Integer.toString(i + 1), defaultTileSize);
			this.gridPane.add(tile.getButton(), 0, 0);
			tile.moveToRelativePosition(i);
			this.addEventHandlerToTile(tile);
			
			this.tiles.add(tile);
		}
	}
	
	private void addEventHandlerToTile(Tile tile) {
		tile.getButton().addEventFilter(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					moveTileToBlankSpot(tile);
					checkSuccess();
				}
			}
		);
		
		tile.getButton().addEventFilter(KeyEvent.KEY_PRESSED,
			new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					KeyCode code = event.getCode();
					if (!tile.getMovementMap().containsKey(code))
						return;

					TileMovement movement = tile.getMovementMap().get(code);	
					Point2D newPosition = tile.getPosition().add(movement.getDirection());
					if (!newPosition.equals(blankTile))
						return;
					
					blankTile = tile.getPosition();
					tile.move(movement);
					checkSuccess();
				}
			}
		);
	}
	
	private void moveTileToBlankSpot(Tile tile) {
		Point2D whereCanMove = whereCanTileMove(tile);
		if (whereCanMove.equals(tile.getPosition()))
			return;
		
		blankTile = tile.getPosition();
		tile.moveToPosition(whereCanMove);
	}
	
	private Point2D whereCanTileMove(Tile tile) {
		Point2D tilePos = tile.getPosition();
		
		// vertical ou horizontal
		boolean isAlignedV = tilePos.getX() == this.blankTile.getX();
		boolean isAlignedH = tilePos.getY() == this.blankTile.getY();
		
		Point2D newPosition = tilePos;
		Point2D distance = new Point2D(0, 0);
		if (!isAlignedH && !isAlignedV) {
			return tilePos;
		} else if (isAlignedH) {
			distance = new Point2D((int)(blankTile.getX() - tilePos.getX()), 0);
		} else if (isAlignedV) {
			distance = new Point2D(0, (int)(blankTile.getY() - tilePos.getY()));
		}
		
		if (distance.getX() > 1 || distance.getY() > 1)
			return tilePos;
		
		newPosition = newPosition.add(distance);
		
		return newPosition;
	}

	// não executa caso pelo menos uma `tile` estiver fora de seu lugar
	private void checkSuccess() {
		if (!this.isComplete())
			return;
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Parabéns!!");
		alert.setHeaderText(null);
		alert.setContentText("Parabéns!! Você ganhou!");
		
		alert.showAndWait();
	}
	
	public boolean isComplete() {
		for (Tile tile : this.tiles) {
			String tileText = tile.getButton().getText();
			String tileRelativePosition = Integer.toString(tiles.indexOf(tile) + 1);
			if (!tileText.equals(tileRelativePosition)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void shuffleTiles() {
		Collections.shuffle(this.tiles);
		for (Tile tile : this.tiles)
			tile.moveToRelativePosition(this.tiles.indexOf(tile));
	}
	
	public GridPane getGridPane() {
		return this.gridPane;
	}
	
	public List<Tile> getTiles() {
		return this.tiles;
	}
	
	public Tile getTileAt(int position) {
		return this.tiles.get(position);
	}
}
