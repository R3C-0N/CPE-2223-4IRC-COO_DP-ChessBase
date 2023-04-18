package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ActionType;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public class CavalierMovementStrategy implements MovementStrategy {
	private static MovementStrategy instance;

	public static MovementStrategy getInstance() {
		if (instance == null)
			instance = new CavalierMovementStrategy();
		return instance;
	}
	@Override
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
		boolean ret = false;

		// cavalier
		if ((Math.abs(xFinal - xInit) + Math.abs(yFinal - yInit)) == 3) {

			if ((Math.abs(xFinal - xInit)<3) && (Math.abs(yFinal - yInit)<3)) {
				ret  = true;
			}
		}

		return ret;
	}
}
