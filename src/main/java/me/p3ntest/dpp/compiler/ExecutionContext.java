package me.p3ntest.dpp.compiler;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
    public HashMap<String, String> variables = new HashMap<>();

    public String replaceVars(String props) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            props = props.replaceAll("$" + entry.getKey(), entry.getValue());
        }

        return props;
    }

    public void setVar(String key, String value) {
        variables.put(key, value);
    }

    public String getVar(String key) {
        return variables.get(key);
    }
}
