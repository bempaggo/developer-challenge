package chat.gpt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;

interface EventListener<T, K> {
    public EventListener<T, K> subscribe(T key, HashSet<ActionEventDelegate<T>> set);

    public EventListener<T, K> subscribe(T key, ActionEventDelegate<T> item);

    public void notify(T key, K item);
}

public class Listener implements KeyListener, EventListener<Integer, Integer> {

    private HashMap<Integer, HashSet<ActionEventDelegate<Integer>>> map = new HashMap<>();

    @Override
    public Listener subscribe(Integer keyCodeEvent, HashSet<ActionEventDelegate<Integer>> set) {
        if (map.containsKey(keyCodeEvent)) {
            map.get(keyCodeEvent).addAll(set);
        } else {
            map.put(keyCodeEvent, set);
        }
        return this;
    }

    @Override
    public Listener subscribe(Integer keyCodeEvent, ActionEventDelegate<Integer> item) {
        subscribe(keyCodeEvent, new HashSet<ActionEventDelegate<Integer>>() {
            {
                add(item);
            }
        });

        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Integer keyCode = e.getKeyCode();
        notify(keyCode, keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void notify(Integer key, Integer item) {
        // TODO Auto-generated method stub
        if (map.containsKey(key)) {
            map.get(key).forEach(x -> x.doAction(item));
        }
    }
}

class GameListener implements EventListener<String, String> {
    private HashMap<String, HashSet<ActionEventDelegate<String>>> map = new HashMap<>();

    @Override
    public EventListener<String, String> subscribe(String key, HashSet<ActionEventDelegate<String>> set) {
        if (map.containsKey(key)) {
            map.get(key).addAll(set);
        } else {
            map.put(key, set);
        }
        return this;
    }

    @Override
    public EventListener<String, String> subscribe(String key, ActionEventDelegate<String> item) {
        subscribe(key, new HashSet<ActionEventDelegate<String>>() {
            {
                add(item);
            }
        });
        return this;
    }

    @Override
    public void notify(String key, String item) {
        if (map.containsKey(key)) {
            map.get(key).forEach(x -> x.doAction(item));
        }
    }

}
