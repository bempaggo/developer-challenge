package chat.gpt.domain.listeners;

import java.util.HashMap;
import java.util.HashSet;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.interfaces.EventListener;


public class NotificationListener implements EventListener<String, String> {
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