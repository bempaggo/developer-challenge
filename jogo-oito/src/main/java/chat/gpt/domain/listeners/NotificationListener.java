package chat.gpt.domain.listeners;

import java.util.HashMap;
import java.util.HashSet;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.interfaces.EventListener;


public class NotificationListener implements EventListener<String, String> {
    private HashMap<String, HashSet<ActionEventDelegate<String>>> map = new HashMap<>();
    private Boolean isEnabled = true;
    
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
        if (isEnabled && map.containsKey(key)) {
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