/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public enum Keyboard {

    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT);

    private final Integer value;

    private static final Map<Integer, Keyboard> map = new HashMap<>();

    static {
        for (Keyboard keyboard : Keyboard.values()) {
            map.put(keyboard.getValue(), keyboard);
        }
    }

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
