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
public class ReineMovementStrategy implements MovementStrategy {
    private static MovementStrategy instance;

    public static MovementStrategy getInstance() {
        if (instance == null) instance = new ReineMovementStrategy();
        return instance;
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
        return Math.abs(yFinal - yInit) == Math.abs(xFinal - xInit) || ((yFinal == yInit) || (xFinal == xInit));
    }

    @Override
    public List<ModelCoord> getMoveItinerary(int xInit, int yInit, int xFinal, int yFinal) {
        List<ModelCoord> ret = new LinkedList<ModelCoord>();

        ret.addAll(getEtapesDiag(xInit, yInit, xFinal, yFinal));
        ret.addAll(getEtapesLigne(xInit, yInit, xFinal, yFinal));

        return ret;
    }


    private List<ModelCoord> getEtapesLigne(int xInit, int yInit, int xFinal, int yFinal) {
        List<ModelCoord> ret = Collections.emptyList();

        // on vérifie que les coordonnées finales sont compatibles
        //avec l'algo de déplacement
        if (this.isAlgoLigneOk(xInit, yInit, xFinal, yFinal)) {

            ret = new LinkedList<ModelCoord>();
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


    private List<ModelCoord> getEtapesDiag(int xInit, int yInit, int xFinal, int yFinal) {
        List<ModelCoord> ret = Collections.emptyList();

        // on vérifie que les coordonnées finales sont compatibles
        //avec l'algo de déplacement
        if (this.isAlgoDiagOk(xInit, yInit, xFinal, yFinal)) {

            ret = new LinkedList<ModelCoord>();
            int xVector = (int) Math.signum(xFinal - xInit);
            int yVector = (int) Math.signum(yFinal - yInit);

            int y = yInit + yVector;
            for (int x = xInit + xVector; x != xFinal; x += xVector) {

                ret.add(new ModelCoord((char) ('a' + x), (8 - y)));
                y += yVector;
            }
        }
        return ret;
    }

    private boolean isAlgoLigneOk(int xInit, int yInit, int xFinal, int yFinal) {

        return (yFinal == yInit) || (xFinal == xInit);
    }

    private boolean isAlgoDiagOk(int xInit, int yInit, int xFinal, int yFinal) {

        return Math.abs(yFinal - yInit) == Math.abs(xFinal - xInit);
    }
}
