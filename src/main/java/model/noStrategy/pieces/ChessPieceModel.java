package model.noStrategy.pieces;

import java.util.List;

import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;

/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 * cette interface définit le comportement attendu des pièces 
 */
public interface ChessPieceModel {

	/**
	 * @return la colonne de la pièce
	 */
	public char getCol();
	
	/**
	 * @return la ligne de la pièce
	 */
	public int getLigne();
	
	
	/**
	 * @return true si la pièce a les coordonnées en paramètre
	 */
	public boolean hasThisCoord(ModelCoord modelCoord);

	/**
	 * @return true si la pièce est de la couleur en paramètre
	 */
	public boolean hasThisColor(PieceSquareColor pieceColor);

//	/**
//	 * @return coordonnées de la pièce
//	 */
//	public ModelCoord getCoord();
//
//	/**
//	 * @return couleur de la piece
//	 */
//	public PieceSquareColor getCouleur();

	/**
	 * @return le nom de la pièce (Tour, Cavalier, etc.)
	 * attention le nom de la pièce ne correspond pas forcément au nom de la classe
	 */
	public String getName();


	/**
	 * @param xFinal
	 * @param yFinal
	 * @return ActionType
	 */
	public ActionType doMove(ModelCoord coord);

	/** 
	 * @return true si piece effectivement captur�e
	 * Positionne x et y à -1
	 */
	public boolean catchPiece();

	/**
	 * @param coord
	 * @param type <- utile uniquement pour le pion qui 
	 * @return true si déplacement légal en fonction des algo
	 * de déplacement spécifique de chaque pièce dans le cas d'un déplacement avec prise
	 */
	public boolean isAlgoMoveOk(ModelCoord finalCoord, ActionType type);

	/**
	 * @param xFinal
	 * @param yFinal
	 * @return la liste des coordonnées des cases traversée en allant vers case de destination
	 */
	public List<ModelCoord> getMoveItinerary(ModelCoord finalCoord);

	/**
	 * Effectue un rollback du dernier mouvement (en rétablissant les coordonnées
	 * initiales de la pièce, telles que mémorisées avant son déplacement).
	 */
	public boolean undoLastMove();

	/**
	 * Effectue un rollback de la capture (en rétablissant les coordonnées
	 * initiales de la pièce au moment de sa capture).
	 */
	public boolean undoLastCatch();

	/**
	 * @return true si la pièce s'est déjà déplacée - utile pour Pion, Tour, Roi
	 */
	public boolean hasMoved();

	
};

