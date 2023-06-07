package chat.gpt.domain.interfaces;

import java.util.HashSet;

public interface EventListener<T, K> {
    public EventListener<T, K> subscribe(T key, HashSet<ActionEventDelegate<K>> set);

    public EventListener<T, K> subscribe(T key, ActionEventDelegate<K> item);

    public void notify(T key, K item);

    public void setEnabled(Boolean cond);
    
    public Boolean getEnabled();
}