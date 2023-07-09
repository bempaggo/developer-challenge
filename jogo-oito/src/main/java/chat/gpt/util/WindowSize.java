package chat.gpt.util;

public enum WindowSize {
    WINDOW_WIDTH(300),
    WINDOW_HEIGHT(300);

    private final Object value;

    WindowSize(Object value) {
        this.value = value;
    }

    public int getMeasure() {
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new UnsupportedOperationException("This enum does not represent the grid size.");
    }
}
