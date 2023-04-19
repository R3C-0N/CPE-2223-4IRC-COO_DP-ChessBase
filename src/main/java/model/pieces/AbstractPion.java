package model.pieces;

import model.strategy.factory.concrete.ModelFactory;
import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;

public abstract class AbstractPion extends AbstractPiece {
    /**
     * @param pieceSquareColor
     * @param coord
     */
    public AbstractPion(PieceSquareColor pieceSquareColor, ModelCoord coord) {
        super(pieceSquareColor, coord);
    }

    /* (non-Javadoc)
     * @see model.AbstractPiece#movePiece(int, int)
     * gère le code de retour lorsqu'il faut promouvoir le pion
     */
    @Override
    public final ActionType doMove(ModelCoord finalCoord){
        ActionType ret = ActionType.UNKNOWN;
        ret = super.doMove(finalCoord);

        if(this.getY() == ModelFactory.nbLigne.get()-1 || this.getY() == 0) {
            ret = ActionType.PROMOTION;
        }
        return ret;
    }
}
