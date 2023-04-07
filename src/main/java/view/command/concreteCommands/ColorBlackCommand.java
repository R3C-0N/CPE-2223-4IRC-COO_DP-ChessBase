package view.command.concreteCommands;

import javafx.scene.paint.Color;
import view.GuiConfig;
import view.command.commands.Command;

/**
 * Command de gestion de la couleur d'affichage des cases
 * de destination possibles d'une PieceGUI sélectionnée
 *
 */
public class ColorBlackCommand implements Command {

	private Color color;

	public ColorBlackCommand(Color color) {
		this.color = color;
	}

	@Override
	public void execute() {
		GuiConfig.blackSquareColor.set(this.color);
	}
}
