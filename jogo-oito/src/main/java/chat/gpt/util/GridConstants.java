package chat.gpt.util;

public enum GridConstants  {
    
    RANDOM_GRID(true),
    GRID_WIDTH(3),
    GRID_SIZE(GRID_WIDTH.getMeasure() * GRID_WIDTH.getMeasure());

    private final Object value;

    GridConstants(Object value) {
        this.value = value;
    }

    public int getMeasure() {
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new UnsupportedOperationException("This enum does not represent the grid size.");
    }

    public boolean useRandomGrid() {
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        throw new UnsupportedOperationException("This enum does not represent the random grid flag.");
    }
}
