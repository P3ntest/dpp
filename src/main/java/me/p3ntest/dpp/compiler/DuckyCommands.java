package me.p3ntest.dpp.compiler;

import java.util.Arrays;
import java.util.Locale;

public enum DuckyCommands {
    STRING(new String[]{"STRING", "TXT", "S", "TYPE"}, (args) -> {
        return "STRING " + args;
    }),
    DEBUG(new String[]{"DEBUG", "LOG", "D"}, (args) -> {
        System.out.println(args);
        return null;
    }),
    DELAY(new String[]{"DELAY", "WAIT", "TIMEOUT"}, (args) -> {
        String[] timeSections = args.trim().split(" ");
        int ms = 0;
        for (String timeSection : timeSections) {
            timeSection = timeSection.trim();
            if (timeSection != "") {
                ms += parseTimeSection(timeSection);
            }
        }
        return "DELAY " + ms;
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

    public String compile(String args) throws DuckyException {
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

    private static int parseTimeSection(String timeSection) throws DuckyException {
        timeSection = timeSection.trim();
        String value = "";
        String identifier = "";
        for (int at = 0; at < timeSection.length(); at++) {
            if (Character.isDigit(timeSection.charAt(at))) {
                value += timeSection.charAt(at);
            } else {
                identifier += timeSection.charAt(at);
            }
        }

        if (identifier == "") identifier = "ms";

        if (value == "") throw new DuckyException();

        int rawValue = Integer.parseInt(value);

        TimeUnits type;

        switch (identifier.toLowerCase()) {
            case "s":
                type = TimeUnits.S;
                break;
            case "ms":
            default:
                type = TimeUnits.MS;
                break;
        }

        return rawValue * type.ms;
    }

    private enum TimeUnits {
        S(1000),
        MS(1);

        private int ms;

        TimeUnits(int ms) {
            this.ms = ms;
        }
    }
}
