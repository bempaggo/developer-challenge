package chat.gpt.domain.listeners;

import java.util.HashMap;
import java.util.HashSet;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.interfaces.EventListener;
import chat.gpt.domain.table.TableCell;

public class MouseListener implements EventListener<String, TableCell> {
    private HashMap<String, HashSet<ActionEventDelegate<TableCell>>> map = new HashMap<>();
    private Boolean isEnabled = true;

    @Override
    public EventListener<String, TableCell> subscribe(String key, HashSet<ActionEventDelegate<TableCell>> set) {
        if (map.containsKey(key)) {
            map.get(key).addAll(set);
        } else {
            map.put(key, set);
        }
        return this;
    }

    @Override
    public EventListener<String, TableCell> subscribe(String key, ActionEventDelegate<TableCell> item) {
        subscribe(key, new HashSet<ActionEventDelegate<TableCell>>() {
            {
                add(item);
            }
        });
        return this;
    }

    @Override
    public void notify(String key, TableCell item) {
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
