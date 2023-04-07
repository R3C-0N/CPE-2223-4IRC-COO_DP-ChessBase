package view.command.invokers.concreteInvokers;

import view.command.commands.Command;
import view.command.invokers.Invoker;

import java.util.Deque;
import java.util.LinkedList;

public class ReplayInvoker<C extends Command> implements Invoker<C> {

    private final Deque<C> undos = new LinkedList<C>();
    private final Deque<C> redos = new LinkedList<C>();

    private final Command reset;

    public ReplayInvoker(Command reset) {
        this.reset = reset;
    }

    @Override
    public void exec(C command) {
        command.execute();
        undos.addLast(command);
        redos.clear();

    }

    @Override
    public void undo() {
        C latestCmd = undos.pollLast();
        if (latestCmd == null) return;
        redos.addLast(latestCmd);
        reset.execute();
        for (C command : undos) {
            command.execute();
        }

    }

    @Override
    public void redo() {
        C latestCmd = redos.pollLast();
        if (latestCmd == null) return;
        latestCmd.execute();
        undos.addLast(latestCmd);
    }

}
