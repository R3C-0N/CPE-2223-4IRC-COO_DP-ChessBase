package model.pieces;

import shared.ModelCoord;
import shared.PieceSquareColor;


/**
 * @author francoise.perrin - Alain BECKER
 * * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 */
public class Roi extends AbstractPiece {
	
	//private boolean isCastling;
	
	/**
	 * @param pieceSquareColor
	 * @param coord
	 */
	public Roi( PieceSquareColor pieceSquareColor, ModelCoord coord) {
		super(pieceSquareColor, coord);
		
	//	this.isCastling = false;
	}
}
