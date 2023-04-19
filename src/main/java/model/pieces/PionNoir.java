package model.pieces;

import shared.ModelCoord;
import shared.PieceSquareColor;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD*
 */
public class PionNoir extends AbstractPion  {
	
	/**
	 * @param couleur
	 * @param coord
	 */
	public PionNoir(PieceSquareColor couleur, ModelCoord coord) {
		super(couleur, coord);

	}
}
