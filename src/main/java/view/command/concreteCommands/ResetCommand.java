package view.command.concreteCommands;

import view.GuiConfig;
import view.command.commands.Command;

/**
 *
 * Command qui permet de réinitialiser l'état des attributs de GuiConfig
 * en cas d'utilisation de la Strategy Replay lors d'un "rollback" (menu undo)
 *
 */
public class ResetCommand implements Command {

	@Override
	public void execute() {
		GuiConfig.setInitState();
	}
	
}
