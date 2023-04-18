package model.strategy.factory.astract;

import model.strategy.adapter.IChessPieceAdaptor;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;

public interface MovementStrategyFactory {
    public MovementStrategy createMovementStrategy(IChessPieceAdaptor adapter);
}
