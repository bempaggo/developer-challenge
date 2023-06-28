package main.java.chat.gpt;

import java.util.List;

public record Board(List<Piece> pieceList) {

    public Piece getPieceUsingPosition(Integer position) throws Exception{
        return this.pieceList.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElseThrow(() -> new Exception("Peca " + position + " nao foi encontrada!"));
    }

    public Piece getPieceUsingValue(Integer value) throws Exception {
        return this.pieceList.stream()
                .filter(piece -> piece.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new Exception("Peca " + value + " nao foi encontrada!"));
    }

    public Piece getEmptyPiece() throws Exception{
        return this.pieceList.stream()
                .filter(Piece::isEmpty)
                .findFirst()
                .orElseThrow(() -> new Exception("Peca vazia nao foi encontrada!"));
    }
}