package model.pieces;

import model.strategy.factory.concrete.ModelFactory;
import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD*
 */
public class PionNoir extends  AbstractPiece  {
	
	/**
	 * @param couleur
	 * @param coord
	 */
	public PionNoir(PieceSquareColor couleur, ModelCoord coord) {
		super(couleur, coord);

	}

	/* (non-Javadoc)
	 * @see model.AbstractPiece#getMoveItinerary(int, int)
	 * dans le cas du pion, il n'y a pas d'itinéraire
	 * puisqu'il se déplace sur une case adjacente
	 * sauf pour le 1er coup où il se déplace de 2 cases
	 */
	@Override
	public List<ModelCoord> getMoveItinerary(ModelCoord finalCoord) {
		int yFinal = 8 - finalCoord.getLigne();
		List<ModelCoord> ret = Collections.emptyList(); 
		if (this.getY()==yFinal-2 || this.getY()==yFinal+2){
			ret = new LinkedList<ModelCoord>();

			int yEtape = (this.getY() + yFinal) / 2;			// Y est la ligne entre départ et arrivée
			ModelCoord coordEtape = new ModelCoord((char)('a'+this.getX()), (8-yEtape));	// et X est dans la même colonne

			ret.add(coordEtape);
		}
		return ret;
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
