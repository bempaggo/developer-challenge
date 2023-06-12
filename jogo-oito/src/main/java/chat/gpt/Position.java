package chat.gpt;

public class Position {
    private Integer line;
    private Integer column;

    public Position(Integer line, Integer column) {
        this.line = line;
        this.column = column;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Position comparPosition = (Position) o;
        if(comparPosition.line != line || comparPosition.column != column){
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return line + column;
    }
}
