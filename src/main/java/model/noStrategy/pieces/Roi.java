package model.noStrategy.pieces;

import model.strategy.movementStrategy.concreteMovementStrategy.RoiMovementStrategy;
import shared.ModelCoord;
import shared.PieceSquareColor;

import java.util.Collections;
import java.util.List;


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
	
	@Override
	public List<ModelCoord> getMoveItinerary(ModelCoord finalCoord) {
		List<ModelCoord> ret = Collections.emptyList(); 

		// on vérifie que les coordonnées finales sont compatibles 
		//avec l'algo de déplacement  dans le cas du roque
		if (this.isAlgoMoveOk(finalCoord, null)) {
			// ToDo
		}
		return ret;
	}
	
}
