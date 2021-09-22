package me.p3ntest.dpp.compiler;

import java.util.Arrays;

public enum DuckyCommands {
    STRING(new String[]{"STRING", "TXT", "S", "TYPE"}, (args) -> {
        return "STRING " + args;
    }),
    DEBUG(new String[]{"DEBUG", "LOG", "D"}, (args) -> {
        System.out.println(args);
        return null;
    }),
    DELAY(new String[]{"DELAY", "WAIT", "TIMEOUT"}, (args) -> {
//        PeriodFormatter formatter = new PeriodFormatterBuilder()
//                .appendDays().appendSuffix("d ")
//                .appendHours().appendSuffix("h ")
//                .appendMinutes().appendSuffix("min")
//                .toFormatter();
//
//        Period p = formatter.parsePeriod("2d 5h 30min");
        return "DELAY " + args;
    }),
    REM(new String[]{"REM", "COMMENT"}, (args) -> {
        return null;
    });

    private final IDuckyCommandCompiler compiler;
    private final String[] aliases;

    DuckyCommands(String[] aliases, IDuckyCommandCompiler compiler) {
        this.compiler = compiler;
        this.aliases = aliases;
    }

    public String compile(String args) {
        return this.compiler.compile(args);
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
