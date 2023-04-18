package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ActionType;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public class FouMovementStrategy implements MovementStrategy {
	private static MovementStrategy instance;

	public static MovementStrategy getInstance() {
		if (instance == null)
			instance = new FouMovementStrategy();
		return instance;
	}

	@Override
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {

		return Math.abs(yFinal - yInit) == Math.abs(xFinal - xInit);
	}
}
