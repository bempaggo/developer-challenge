/**
 * file: Game.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 16, 2023
 */

package com.j0suetm.eight_game;

import com.j0suetm.eight_game.components.Board;
import com.j0suetm.eight_game.components.RestartButton;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game {
	private Board board;
	private String title;
	private Point2D size;
	private RestartButton restartButton;
	private Stage stage;
	private Scene scene;
	private StackPane stackPane;
	
	public Game(String title, int width, int height, boolean shouldShuffle) {
		this.title = title;
		this.size = new Point2D(width, height);
		
		this.board = new Board(width, height - 40, shouldShuffle);
		this.restartButton = new RestartButton((int)this.size.getX(), 40);
		this.restartButton.addEventHandler(this.board);
		
		this.stackPane = new StackPane(this.restartButton.getButton(), this.board.getGridPane());
		this.stackPane.setAlignment(Pos.BOTTOM_CENTER);
		
		this.scene = new Scene(this.stackPane, this.size.getX(),this.size.getY());
	}
	
	public void show(Stage stage) {
		this.stage = stage;
		this.stage.setTitle(this.title);
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public StackPane getStackPane() {
		return stackPane;
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point2D getSize() {
		return size;
	}

	public void setSize(Point2D size) {
		this.size = size;
	}

	public RestartButton getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(RestartButton restartButton) {
		this.restartButton = restartButton;
	}
}
