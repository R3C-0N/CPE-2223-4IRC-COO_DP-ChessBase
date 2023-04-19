package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.AbstractPionMovementStrategy;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ModelCoord;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PionBlancMovementStrategy extends AbstractPionMovementStrategy {
    private static MovementStrategy instance;

    public static MovementStrategy getInstance() {
        if (instance == null)
            instance = new PionBlancMovementStrategy();
        return instance;
    }
    @Override
    protected boolean isAlgoMoveVerticalOk(int yInit, int yFinal) {
        return yFinal - yInit < 0;
    }

    @Override
    protected boolean isAlgoMoveDiagonalOk(int xInit, int yInit, int xFinal, int yFinal) {
        return (yFinal == yInit - 1 && xFinal == xInit + 1) || (yFinal == yInit - 1 && xFinal == xInit - 1);
    }

    @Override
    public List<ModelCoord> getMoveItinerary(int xInit, int yInit, int xFinal, int yFinal) {
        List<ModelCoord> ret = Collections.emptyList();
        if (yInit==yFinal-2 || yInit==yFinal+2){
            ret = new LinkedList<ModelCoord>();

            int yEtape = (yInit + yFinal) / 2;			// Y est la ligne entre départ et arrivée
            ModelCoord coordEtape = new ModelCoord((char)('a'+xInit), (8-yEtape));	// et X est dans la même colonne

            ret.add(coordEtape);
        }
        return ret;
    }
}
