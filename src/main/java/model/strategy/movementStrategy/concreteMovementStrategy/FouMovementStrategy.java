package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ActionType;
import shared.ModelCoord;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


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

	@Override
	public List<ModelCoord> getMoveItinerary(int xInit, int yInit, int xFinal, int yFinal) {
		List<ModelCoord> ret = Collections.emptyList();

		// on vérifie que les coordonnées finales sont compatibles
		// avec l'algo de déplacement
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal, false, null)) {

			ret = new LinkedList<ModelCoord>();
			int xVector = (int) Math.signum(xFinal - xInit);
			int yVector = (int) Math.signum(yFinal - yInit);

			int y = yInit + yVector;
			for (int x = xInit + xVector ; x!=xFinal ; x+=xVector) {

				ret.add(new ModelCoord((char)('a'+x), (8-y)));

				y+=yVector;
			}
		}

		return ret;
	}
}
