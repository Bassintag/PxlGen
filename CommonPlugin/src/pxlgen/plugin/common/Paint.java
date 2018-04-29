package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * paint.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */

@FunctionHandler
public class Paint {

    @Function
    public void paint(ImageBuffer buffer, Float red, Float green, Float blue) {
        Color c = new Color(red, green, blue);
        buffer.eachPixel((color) -> (c), false);
    }

}
