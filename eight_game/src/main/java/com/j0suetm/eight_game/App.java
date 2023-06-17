/**
 * file: App.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 16, 2023
 */

package com.j0suetm.eight_game;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	@Override
  public void start(Stage stage) {
		Window window = new Window("Jogo do 8 - Josué Teodoro", 600, 640);
		window.show(stage);
  }

  public static void main(String[] args) {
  	launch(args);
  }
}