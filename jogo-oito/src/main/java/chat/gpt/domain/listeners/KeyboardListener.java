package chat.gpt.domain.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.interfaces.EventListener;

public class KeyboardListener implements KeyListener, EventListener<Integer, Integer> {

    private HashMap<Integer, HashSet<ActionEventDelegate<Integer>>> map = new HashMap<>();
    private Boolean isEnabled = true;

    @Override
    public EventListener<Integer, Integer> subscribe(Integer keyCodeEvent, HashSet<ActionEventDelegate<Integer>> set) {
        if (map.containsKey(keyCodeEvent)) {
            map.get(keyCodeEvent).addAll(set);
        } else {
            map.put(keyCodeEvent, set);
        }
        return this;
    }

    @Override
    public EventListener<Integer, Integer> subscribe(Integer keyCodeEvent, ActionEventDelegate<Integer> item) {
        subscribe(keyCodeEvent, new HashSet<ActionEventDelegate<Integer>>() {
            {
                add(item);
            }
        });
        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer keyCode = e.getKeyCode();
        notify(keyCode, keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void notify(Integer key, Integer item) {
        if (map.containsKey(key)) {
            map.get(key).forEach(x -> x.doAction(item));
        }
    }

    @Override
    public void setEnabled(Boolean cond) {
        isEnabled = cond;
    }

    @Override
    public Boolean getEnabled() {
        return isEnabled;
    }
}