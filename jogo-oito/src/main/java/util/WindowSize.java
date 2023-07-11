package util;

public enum WindowSize {
    WINDOW_WIDTH(300),
    WINDOW_HEIGHT(300);

    private final Object value;

    WindowSize(Object value) {
        this.value = value;
    }

    public Integer getMeasure() {
        return (Integer) value;
    }
}
