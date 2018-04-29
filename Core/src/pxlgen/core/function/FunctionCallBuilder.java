package pxlgen.core.function;

import java.util.ArrayList;
import java.util.List;

/**
 * FunctionCallBuilder.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class FunctionCallBuilder {

    private final String name;

    private List<Object> params;

    public FunctionCallBuilder(String name) {
        this.name = name;
        params = new ArrayList<>();
    }

    public FunctionCallBuilder param(Object param) {
        params.add(param);
        return this;
    }

    public FunctionCall build() {
        return new FunctionCall(name, params.toArray());
    }
}
