package me.p3ntest.dpp.compiler;

public class AssignmentCommand extends Command {

    private String key;
    private String value;

    public AssignmentCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public AssignmentCommand(String command) throws DuckyException {
        String[] parts = command.trim().split("=");

        if (parts.length != 2) {
            throw new DuckyException();
        }

        String key = parts[0].trim().substring(1);
        String value = parts[1].trim();

        this.key = key;
        this.value = value;
    }

    @Override
    public String compile(ExecutionContext context) {
        context.setVar(key, value);
        return null;
    }
}
