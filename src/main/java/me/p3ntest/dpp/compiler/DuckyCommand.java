package me.p3ntest.dpp.compiler;

public class DuckyCommand extends Command{

    public DuckyCommands type;
    public String args;

    public DuckyCommand(DuckyCommands type, String args) {
        this.type = type;
        this.args = args;
    }

    public String compile(ExecutionContext context) {
        return type.compile(context.replaceVars(args));
    }

}
