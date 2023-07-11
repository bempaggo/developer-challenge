package util;

public enum GridConstants {

    RANDOMNESS(true),
    WIDTH(3),
    SIZE(WIDTH.getMeasure() * WIDTH.getMeasure());

    private final Object value;

    GridConstants(Object value) {
        this.value = value;
    }

    public Integer getMeasure() {
        return (Integer) value;
    }

    public Boolean useRandomGrid() {
        return (Boolean) value;
    }
}
