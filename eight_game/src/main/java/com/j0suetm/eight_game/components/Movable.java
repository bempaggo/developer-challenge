/**
 * file: components/Movable.java
 * author: Josué Teodoro Moreira <teodoro.josue@pm.me>
 * date: Jun 17, 2023
 */

package com.j0suetm.eight_game.components;

import javafx.geometry.Point2D;

public interface Movable {
	public void moveTo(TileMovement direction);
	public void moveToPosition(Point2D newPosition);
	public void moveToRelativePosition(int relativePosition);
}
