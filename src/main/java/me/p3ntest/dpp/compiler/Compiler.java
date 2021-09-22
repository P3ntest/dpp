package me.p3ntest.dpp.compiler;

public abstract class Compiler {
    public static String compileModule(Module module) {
        return compileModule(module, new ExecutionContext());
    }

    public static String compileModule(Module module, ExecutionContext context) {
        String code = "";

        for (Command command : module.commands) {
            String compiledCommand = command.compile(context);
            if (compiledCommand != null) {
                code += compiledCommand + "\n";
            }
        }

        return code;
    }
}
