package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

/**
 * Clear.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */

@FunctionHandler
public class Clear {

    @Function
    public void clear(ImageBuffer buffer) {
        buffer.eachPixel((c) -> (null));
    }
}
