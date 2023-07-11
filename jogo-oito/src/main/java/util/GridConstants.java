package util;

public enum GridConstants {

    RANDOM_GRID(true),
    GRID_WIDTH(3),
    GRID_SIZE(GRID_WIDTH.getMeasure() * GRID_WIDTH.getMeasure());

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
