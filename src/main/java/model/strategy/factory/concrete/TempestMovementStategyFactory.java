package model.strategy.factory.concrete;

import model.strategy.adapter.IChessPieceAdaptor;
import model.strategy.factory.astract.AbstractMovementStrategyFactory;
import model.strategy.factory.astract.MovementStrategyFactory;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;

import java.util.HashMap;
import java.util.Map;

public class TempestMovementStategyFactory extends AbstractMovementStrategyFactory implements MovementStrategyFactory {
    protected Map<Integer, String> positionRole;

    private void initPositionRole() {
        this.positionRole = new HashMap<>();
        this.positionRole.put(0, "Tour");
        this.positionRole.put(1, "Cavalier");
        this.positionRole.put(2, "Fou");
        this.positionRole.put(5, "Fou");
        this.positionRole.put(6, "Cavalier");
        this.positionRole.put(7, "Tour");
    }

    public static MovementStrategyFactory getInstance() {
        if (!(instance instanceof TempestMovementStategyFactory)) {
            TempestMovementStategyFactory factory = new TempestMovementStategyFactory();
            factory.initPositionRole();
            instance = factory;
        }
        return instance;
    }

    @Override
    public MovementStrategy createMovementStrategy(IChessPieceAdaptor adapter) {
        if (this.positionRole.containsKey(adapter.getCol()))
            return TempestMovementStategyFactory.getStrategies().get(this.positionRole.get(adapter.getCol()));
        return TempestMovementStategyFactory.getStrategies().get(adapter.getPieceName());
    }
}
