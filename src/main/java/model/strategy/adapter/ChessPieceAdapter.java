package model.strategy.adapter;

import model.pieces.ChessPieceModel;

public class ChessPieceAdapter implements IChessPieceAdaptor {
    private final ChessPieceModel piece;
    public ChessPieceAdapter(ChessPieceModel chessPieceModel) {
        this.piece = chessPieceModel;
    }
    
    public String getPieceName() {
        return this.piece.getName();
    }
    
    public int getCol() {
        return this.piece.getCol() - 'a';
    }
}
