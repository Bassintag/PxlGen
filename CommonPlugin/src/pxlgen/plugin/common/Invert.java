package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * Invert.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Invert {

    @Function(description = "Replaces empty spaces with a color and everything else with nothing")
    public void invert(ImageBuffer imageBuffer,
                       @Name("red") float r,
                       @Name("green") float g,
                       @Name("blue") float b) {
        Color color = new Color(r, g, b);
        imageBuffer.eachPixel((c) -> c == null ? color : null);
    }

    @Function(description = "Replaces empty spaces with white and everything else with nothing")
    public void invert(ImageBuffer imageBuffer) {
        invert(imageBuffer, 1, 1, 1);
    }
}
