package model.pieces;

import shared.ModelCoord;
import shared.PieceSquareColor;

import java.util.Collections;
import java.util.List;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public class Cavalier extends AbstractPiece  {
	

	/**
	 * @param pieceSquareColor
	 * @param coord
	 */
	public Cavalier( PieceSquareColor pieceSquareColor, ModelCoord coord) {
		super(pieceSquareColor, coord);
	}

	/* (non-Javadoc)
	 * @see model.AbstractPiece#getMoveItinerary(int, int)
	 * dans le cas du cavalier, il n'y a pas d'itinéraire
	 * puisqu'il peut enjamber les autres pièces
	 */
	@Override
	public List<ModelCoord> getMoveItinerary(ModelCoord finalCoord) {
		List<ModelCoord> ret = Collections.emptyList(); 
		return ret;
	}

	
}
