package util;

public enum BoardConstants {

    WIDTH(3),
    SIZE(WIDTH.getMeasure() * WIDTH.getMeasure());

    private final Object value;

    BoardConstants(Object value) {
        this.value = value;
    }

    public Integer getMeasure() {
        return (Integer) value;
    }

}
