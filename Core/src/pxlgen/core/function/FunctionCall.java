package pxlgen.core.function;

/**
 * FunctionCall.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class FunctionCall {

    private final String name;

    private final Object[] params;

    public FunctionCall(String name, Object[] params)
    {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public Object[] getParams() {
        return params;
    }
}
