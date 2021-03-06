package pxlgen.core.command;

import pxlgen.core.function.FunctionCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Command.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class Command {

    private final String domain;

    private final String name;

    private List<Object> params;

    public Command(String name) {
        this(name, null);
    }

    public Command(String name, String domain) {
        this.name = name;
        this.domain = domain;
        params = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addParam(Object param) {
        params.add(param);
    }

    public Object getParam(int i) {
        return params.get(i);
    }

    public int getParamsCount() {
        return params.size();
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (domain != null)
            ret.append(domain).append(".");
        ret.append(name).append("(");
        for (int i = 0; i < params.size(); i += 1) {
            if (i > 0)
                ret.append(", ");
            ret.append(params.get(i).toString());
        }
        ret.append(")");
        return ret.toString();
    }

    public FunctionCall toFunctionCall() {
        return new FunctionCall(getName(), params.toArray());
    }
}
