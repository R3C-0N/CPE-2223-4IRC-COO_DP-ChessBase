package model.strategy.movementStrategy.abstractMovementStrategy;

import shared.ActionType;

/**
 * @author francoise.perrin
 * 
 * Interface des différentes Stratégies de Mouvement des pièces
 */

public interface MovementStrategy {
	
	/**
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @param hasMoved // utile pour Pion, Roi, Tour
	 * @param actionType // Utile pour Pion qui se déplace différement en cas de prise
	 * @return true si déplacement légal en fonction 
	 * des algo de déplacement spécifique de chaque pièce
	 */
	boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType);
	
}
