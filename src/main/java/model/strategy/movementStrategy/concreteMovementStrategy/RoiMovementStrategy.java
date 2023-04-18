package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ActionType;


/**
 * @author francoise.perrin - Alain BECKER
 * * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 */
public class RoiMovementStrategy implements MovementStrategy {
    private static MovementStrategy instance;

    public static MovementStrategy getInstance() {
        if (instance == null)
            instance = new RoiMovementStrategy();
        return instance;
    }
    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
        // Cas du roque
        // TODO : implémenter

        // cas général
        return (Math.abs(yFinal - yInit) <= 1) && (Math.abs(xFinal - xInit) <= 1);
    }
}
