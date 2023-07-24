package model;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Keyboard {

    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT);

    private final Integer value;

    private static final Map<Integer, Keyboard> map = Arrays.stream(Keyboard.values())
            .collect(Collectors.toMap(Keyboard::getValue, Function.identity()));

    Keyboard(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Keyboard fromValue(Integer value) {
        return map.get(value);
    }
}
