/**
 * file: components/TileMovement.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 17, 2023
 */

package com.j0suetm.eight_game.components;

import javafx.geometry.Point2D;

public enum TileMovement {
	LEFT(new Point2D(-1, 0)),
	RIGHT(new Point2D(1, 0)),
	UP(new Point2D(0, -1)),
	DOWN(new Point2D(0, 1));
	
	private Point2D direction;
	
	TileMovement(Point2D direction) {
		this.direction = direction;
	}
	
	public Point2D getDirection () {
		return this.direction;
	}
}
