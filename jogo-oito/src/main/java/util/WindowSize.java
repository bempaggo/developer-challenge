package util;

public enum WindowSize {
    WIDTH(300),
    HEIGHT(300);

    private final Integer value;

    WindowSize(Integer value) {
        this.value = value;
    }

    public Integer getMeasure() {
        return (Integer) value;
    }
}
