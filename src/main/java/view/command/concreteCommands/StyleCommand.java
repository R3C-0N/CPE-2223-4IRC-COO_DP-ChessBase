package view.command.concreteCommands;

import view.GuiConfig;
import view.PaintStyle;
import view.command.commands.Command;

/**
 *
 * Command de gestion du style d'affichage des cases du damier
 * uni  isColorGradient = false) ou dégradé (isColorGradient = true)
 *
 */
public class StyleCommand implements Command {

	private PaintStyle paintStyle;

	public StyleCommand(PaintStyle paintStyle) {
		this.paintStyle = paintStyle;
	}
	
	@Override
	public void execute() {
		GuiConfig.paintStyle.set(this.paintStyle);
	}

}
