package model;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameKeyboard {
	UP(KeyEvent.VK_UP), DOWN(KeyEvent.VK_DOWN), LEFT(KeyEvent.VK_LEFT), RIGHT(KeyEvent.VK_RIGHT);

	private final int keyCode;

	private static final Map<Integer, GameKeyboard> KEY_MAP = Arrays.stream(GameKeyboard.values())
			.collect(Collectors.toMap(GameKeyboard::getKeyCode, Function.identity()));

	GameKeyboard(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public static GameKeyboard fromKeyCode(int keyCode) {
		return KEY_MAP.get(keyCode);
	}
}
