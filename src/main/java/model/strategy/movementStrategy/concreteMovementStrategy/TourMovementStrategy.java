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
public class TourMovementStrategy implements MovementStrategy {
    private static MovementStrategy instance;

    public static MovementStrategy getInstance() {
        if (instance == null) instance = new TourMovementStrategy();
        return instance;
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
        return (yFinal == yInit) || (xFinal == xInit);
    }

    @Override
    public List<ModelCoord> getMoveItinerary(int xInit, int yInit, int xFinal, int yFinal) {
        List<ModelCoord> ret = Collections.emptyList();

        // on vérifie que les coordonnées finales sont compatibles 
        //avec l'algo de déplacement 
        if (this.isMoveOk(xInit, yInit, xFinal, yFinal, false, null)) {

            ret = new LinkedList<>();
            int xVector = xFinal - xInit;
            int yVector = yFinal - yInit;

            // mouvement sur l'axe des X (y fixe)
            if (xVector != 0) {

                int step = (int) Math.signum(xVector);    // déplacement par pas de +1 ou -1
                for (int x = xInit + step; x != xFinal; x += step) {
                    ret.add(new ModelCoord((char) ('a' + x), (8 - yInit)));
                }
            }
            // mouvement sur l'axe des Y (x fixe)
            else {
                int step = (int) Math.signum(yVector);    // déplacement par pas de +1 ou -1
                for (int y = yInit + step; y != yFinal; y += step) {
                    ret.add(new ModelCoord((char) ('a' + xInit), (8 - y)));
                }
            }
        }
        return ret;
    }
}
