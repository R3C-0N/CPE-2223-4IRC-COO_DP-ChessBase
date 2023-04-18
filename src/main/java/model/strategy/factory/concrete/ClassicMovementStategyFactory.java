package model.strategy.factory.concrete;

import model.strategy.adapter.IChessPieceAdaptor;
import model.strategy.factory.astract.AbstractMovementStrategyFactory;
import model.strategy.factory.astract.MovementStrategyFactory;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;

public class ClassicMovementStategyFactory extends AbstractMovementStrategyFactory implements MovementStrategyFactory {

    public static MovementStrategyFactory getInstance() {
        if (!(instance instanceof ClassicMovementStategyFactory))
            instance = new ClassicMovementStategyFactory();
        return instance;
    }
    @Override
    public MovementStrategy createMovementStrategy(IChessPieceAdaptor adapter) {
        String pieceName = adapter.getPieceName();
        return ClassicMovementStategyFactory.getStrategies().get(pieceName);
    }
}
