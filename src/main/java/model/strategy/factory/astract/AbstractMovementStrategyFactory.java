package model.strategy.factory.astract;

import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import model.strategy.movementStrategy.concreteMovementStrategy.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMovementStrategyFactory implements MovementStrategyFactory {
    protected static MovementStrategyFactory instance = null;

    private static final Map<String, MovementStrategy> strategies = new HashMap<String, MovementStrategy>();

    static {
        AbstractMovementStrategyFactory.strategies.put("PionBlanc", PionBlancMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("PionNoir", PionNoirMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("Tour", TourMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("Cavalier", CavalierMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("Fou", FouMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("Reine", ReineMovementStrategy.getInstance());
        AbstractMovementStrategyFactory.strategies.put("Roi", RoiMovementStrategy.getInstance());
    }

    protected static Map<String, MovementStrategy> getStrategies() {
        return strategies;
    }
}
