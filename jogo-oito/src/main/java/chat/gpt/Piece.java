package chat.gpt;

public class Piece {
    private Integer pieceValue;

    public Piece(Integer pieceValue) {
        this.pieceValue = pieceValue;
    }

    public Integer getPieceValue() {
        return pieceValue;
    }

    public void setPieceValue(Integer pieceValue) {
        this.pieceValue = pieceValue;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Piece piece = (Piece) object;

        if (pieceValue != null ? !pieceValue.equals(piece.pieceValue) : piece.pieceValue != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pieceValue != null ? pieceValue.hashCode() : 0);
        return result;
    }
}
