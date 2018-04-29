package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
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

    @Function
    public void invert(ImageBuffer imageBuffer, float r, float g, float b) {
        Color color = new Color(r, g, b);
        imageBuffer.eachPixel((c) -> c == null ? color : null);
    }

    @Function
    public void invert(ImageBuffer imageBuffer) {
        invert(imageBuffer, 1, 1, 1);
    }
}
