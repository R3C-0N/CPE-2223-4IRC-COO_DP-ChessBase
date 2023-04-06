package view.decorator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import shared.GUICoord;
import view.ChessSquareGui;
import view.GuiConfig;
import view.PaintStyle;

public class SquareGuiDecorator extends BorderPane implements ChessSquareGui, ChangeListener {
    protected ChessSquareGui chessSquareGui;

    public SquareGuiDecorator(ChessSquareGui chessSquareGui) {
        super();
        this.chessSquareGui = chessSquareGui;

        // Pour que le carré soit bien affiché
        this.setCenter((Node) this.chessSquareGui);

        // On ajoute le comportement de ChangeListener pour que le carré réagisse aux changements de couleur/style
        GuiConfig.blackSquareColor.addListener((ChangeListener<Color>) this);
        GuiConfig.whiteSquareColor.addListener((ChangeListener<Color>) this);
        GuiConfig.paintStyle.addListener((ChangeListener<PaintStyle>) this);
    }

    @Override
    public GUICoord getCoord() {
        return this.chessSquareGui.getCoord();
    }

    @Override
    public void resetColor(boolean isLight) {
        this.chessSquareGui.resetColor(isLight);
    }

    @Override
    public void paint() {
        this.chessSquareGui.paint();
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        this.paint();
    }
}
