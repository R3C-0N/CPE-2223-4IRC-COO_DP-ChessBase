package model.strategy.adapter;

import model.noStrategy.pieces.ChessPieceModel;

public class ChessPieceAdapter implements IChessPieceAdaptor {
    private final String pieceName;
    private final Character col;
    public ChessPieceAdapter(ChessPieceModel chessPieceModel) {
        this.pieceName = chessPieceModel.getName();
        this.col = chessPieceModel.getCol();
    }
    
    public String getPieceName() {
        return pieceName;
    }
    
    public int getCol() {
        return col - 'a';
    }
}
