package me.p3ntest.dpp.compiler;

import java.util.Arrays;

public enum DuckyCommands {
    STRING(new String[]{"", ""}, (args) -> {
        return "";
    }),
    REM(new String[]{"", ""}, (args) -> {
        return "";
    });

    private final IDuckyCommandCompiler compiler;
    private final String[] aliases;

    DuckyCommands(String[] aliases, IDuckyCommandCompiler compiler) {
        this.compiler = compiler;
        this.aliases = aliases;
    }

    public String compile(String args) {
        return compile(args);
    }

    public static DuckyCommands parseKeyWord(String keyWord) {
        for (DuckyCommands command : DuckyCommands.values()) {
            if (command.aliases != null) {
                if (Arrays.asList(command.aliases).contains(keyWord.toUpperCase())) {
                    return command;
                }
            }
        }

        return null;
    }
}
