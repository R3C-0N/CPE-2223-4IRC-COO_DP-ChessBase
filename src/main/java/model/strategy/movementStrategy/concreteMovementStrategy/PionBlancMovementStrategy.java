package model.strategy.movementStrategy.concreteMovementStrategy;

import model.strategy.movementStrategy.abstractMovementStrategy.AbstractPionMovementStrategy;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;

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
}
