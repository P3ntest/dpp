package me.p3ntest.dpp.compiler;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendSeconds().appendSuffix("s ")
                .appendMillis().appendSuffix("ms")
                .toFormatter();

        Period p = formatter.parsePeriod(args);
        return "DELAY " + p.getMillis();
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
