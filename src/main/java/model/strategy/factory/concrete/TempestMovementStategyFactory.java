package model.strategy.factory.concrete;

import model.strategy.adapter.IChessPieceAdaptor;
import model.strategy.factory.astract.AbstractMovementStrategyFactory;
import model.strategy.factory.astract.MovementStrategyFactory;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;

import java.util.HashMap;
import java.util.Map;

public class TempestMovementStategyFactory extends AbstractMovementStrategyFactory implements MovementStrategyFactory {
    protected Map<Integer, String> positionStrategy;

    public void initPositionStrategy() {
        this.positionStrategy = new HashMap<>();
        this.positionStrategy.put(0, "Tour");
        this.positionStrategy.put(1, "Cavalier");
        this.positionStrategy.put(2, "Fou");
        this.positionStrategy.put(5, "Fou");
        this.positionStrategy.put(6, "Cavalier");
        this.positionStrategy.put(7, "Tour");
    }

    public static MovementStrategyFactory getInstance() {
        if (!(instance instanceof TempestMovementStategyFactory)) {
            TempestMovementStategyFactory factory = new TempestMovementStategyFactory();
            factory.initPositionStrategy();
            instance = factory;
        }
        return instance;
    }
    @Override
    public MovementStrategy createMovementStrategy(IChessPieceAdaptor adapter) {
        if (this.positionStrategy.containsKey(adapter.getCol()))
            return TempestMovementStategyFactory.getStrategies().get(this.positionStrategy.get(adapter.getCol()));

        return TempestMovementStategyFactory.getStrategies().get(adapter.getPieceName());
    }
}
