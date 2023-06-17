/**
 * file: Window.java
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

public class Window {
	private Scene scene;
	private StackPane stackPane;
	private Board board;
	private String title;
	private Point2D size;
	private RestartButton restartButton;
	
	public Window(String title, int width, int height) {
		this.title = title;
		this.size = new Point2D(width, height);
		
		this.board = new Board(width, height - 40, true);
		this.restartButton = new RestartButton((int)this.size.getX(), 40);
		this.restartButton.addEventHandler(this.board);
		
		this.stackPane = new StackPane(this.restartButton.getButton(), this.board.getGridPane());
		this.stackPane.setAlignment(Pos.BOTTOM_CENTER);
		
		this.scene = new Scene(this.stackPane, this.size.getX(),this.size.getY());
	}
	
	public void show(Stage stage) {
		stage.setTitle(this.title);
		stage.setScene(this.scene);
		stage.show();
	}
}
