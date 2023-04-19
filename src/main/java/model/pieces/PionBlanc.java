package model.pieces;

import model.strategy.factory.concrete.ModelFactory;
import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;


/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD*
 */
public class PionBlanc extends  AbstractPiece  {

	/**
	 * @param couleur
	 * @param coord
	 */
	public PionBlanc(PieceSquareColor couleur, ModelCoord coord) {
		super(couleur, coord);

	}




	/* (non-Javadoc)
	 * @see model.AbstractPiece#movePiece(int, int)
	 * gère le code de retour lorsqu'il faut promouvoir le pion
	 */
	@Override
	public ActionType doMove(ModelCoord finalCoord){
		ActionType ret = ActionType.UNKNOWN;
		ret = super.doMove(finalCoord);

		if(this.getY() == ModelFactory.nbLigne.get()-1 || this.getY() == 0) {
			ret = ActionType.PROMOTION;
		}
		return ret;
	}
}
