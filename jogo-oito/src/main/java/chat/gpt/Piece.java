package main.java.chat.gpt;

public class Piece extends ValueInterface{

    private Integer position;
    public Piece(Integer value, Integer position) {
        this.value = value;
        this.position = position;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStringValue() {
        return this.value == 9 ? " " : this.value.toString();
    }

    public Boolean isEmpty() {
        return this.value == 9;
    }

    public Boolean rightPosition() {
        return this.getPosition().equals(this.getValue() - 1);
    }

}
