package chat.gpt.domain.interfaces;

import java.util.HashSet;

public interface EventListener<T, K> {
    public EventListener<T, K> subscribe(T key, HashSet<ActionEventDelegate<T>> set);

    public EventListener<T, K> subscribe(T key, ActionEventDelegate<T> item);

    public void notify(T key, K item);
}