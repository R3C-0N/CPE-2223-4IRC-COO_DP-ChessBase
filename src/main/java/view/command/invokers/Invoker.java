package view.command.invokers;

public interface Invoker<C> {
    void exec(C command);

    void undo();

    void redo();
}
