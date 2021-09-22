package me.p3ntest.dpp.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Interpreter {

    public static Module interpretModule(String text) throws DuckyException {
        String[] lines = text.split("\n");

        Module module = new Module();

        for (String line : lines) {
            Command command = interpretCommand(line);

            if (command != null) {
                module.commands.add(interpretCommand(line));
            }
        }

        return module;
    }

    private static Command interpretCommand(String text) throws DuckyException{
        text = text.trim();

        if (text == "") return null;

        String keyWord = text.split(" ")[0];

        if (DuckyCommands.parseKeyWord(keyWord) != null) {
            String args = text.substring(keyWord.length() + 1);
            return new DuckyCommand(DuckyCommands.parseKeyWord(keyWord), args);
        } else if (keyWord.startsWith("$")) {
            return new AssignmentCommand(text);
        } else {
            return null;
        }
    }

    public static String[] purifyArgs(String[] args) {
        List<String> pureArgs = new ArrayList<>();

        for (String arg : args) {
            if (arg.trim() != "") {
                pureArgs.add(arg.trim());
            }
        }

        String[] output = new String[pureArgs.size()];
        return pureArgs.toArray(output);
    }

}
