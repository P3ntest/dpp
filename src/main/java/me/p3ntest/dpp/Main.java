package me.p3ntest.dpp;

import me.p3ntest.dpp.compiler.Compiler;
import me.p3ntest.dpp.compiler.DuckyException;
import me.p3ntest.dpp.compiler.ExecutionContext;
import me.p3ntest.dpp.compiler.Interpreter;

public class Main {

    public static void main(String[] args) throws DuckyException {
        ExecutionContext context = new ExecutionContext();
        context.setVar("HEY", "world");

        System.out.println(Compiler.compileModule(Interpreter.interpretModule("TXT $HEY\n $HEY = BONJOUR\nDELAY 1s 1ms 3 \nSTRING $HEY"), context));
    }

}
