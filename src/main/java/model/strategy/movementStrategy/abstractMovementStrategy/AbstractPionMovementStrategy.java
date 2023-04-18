package model.strategy.movementStrategy.abstractMovementStrategy;

import shared.ActionType;

public abstract class AbstractPionMovementStrategy implements MovementStrategy {

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
        boolean ret = false;

        // Déplacement d'1 case en diagonale avec prise
        if (actionType == ActionType.TAKE) {

            return isAlgoMoveDiagonalOk(xInit, yInit, xFinal, yFinal);
        }
        // Déplacement vertical sans prise
        // d'1 case si le pion a déjà bougé, de 2 cases sinon
        // vers le haut ou vers le bas selon sa couleur
        else {
            if ((xFinal == xInit) && (Math.abs(yFinal - yInit) <= 1 || (Math.abs(yFinal - yInit) <= 2 && !hasMoved))) {
                ret = isAlgoMoveVerticalOk(yInit, yFinal);
            }
        }
        return ret;
    }

    protected abstract boolean isAlgoMoveVerticalOk(int yInit, int yFinal);

    protected abstract boolean isAlgoMoveDiagonalOk(int xInit, int yInit, int xFinal, int yFinal);
}
