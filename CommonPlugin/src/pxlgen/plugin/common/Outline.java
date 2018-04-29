package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

/**
 * Outline.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Outline {

    @Function
    public void outline(ImageBuffer imageBuffer) {
        ImageBuffer tmp = imageBuffer.clone();
        tmp.eachPixel((c, x, y) -> {
            if (imageBuffer.getAt(x - 1, y) == null ||
                    imageBuffer.getAt(x + 1, y) == null ||
                    imageBuffer.getAt(x, y - 1) == null ||
                    imageBuffer.getAt(x, y + 1) == null)
                return c;
            return null;
        });
        imageBuffer.setFrom(tmp);
    }
}
