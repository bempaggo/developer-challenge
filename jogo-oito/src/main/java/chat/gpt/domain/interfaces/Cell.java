package chat.gpt.domain.interfaces;
public interface Cell<T> {
    void setValue(T value);

    T getValue();

    Boolean isEmpty();
}