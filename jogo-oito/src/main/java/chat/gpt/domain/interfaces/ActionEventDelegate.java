package chat.gpt.domain.interfaces;

@FunctionalInterface
public interface ActionEventDelegate<T> {
    void doAction(T dataEvent);
}
