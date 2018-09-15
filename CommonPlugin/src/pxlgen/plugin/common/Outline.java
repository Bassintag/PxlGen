package pxlgen.plugin.common;

import pxlgen.core.Direction;
import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
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

    @Function(description = "Clears everything but the outlines of all areas")
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

    @Function(description = "Clears everything but the outlines of all areas in a direction")
    public void outline(ImageBuffer imageBuffer,
                        @Name("direction") Direction direction) {
        ImageBuffer tmp = imageBuffer.clone();
        boolean left = direction.getRelX() < 0;
        boolean right = direction.getRelX() > 0;
        boolean top = direction.getRelY() < 0;
        boolean bottom = direction.getRelY() > 0;
        tmp.eachPixel((c, x, y) -> {
            if ((imageBuffer.getAt(x - 1, y) == null && left) ||
                    (imageBuffer.getAt(x + 1, y) == null && right) ||
                    (imageBuffer.getAt(x, y - 1) == null && top) ||
                    (imageBuffer.getAt(x, y + 1) == null && bottom))
                return c;
            return null;
        });
        imageBuffer.setFrom(tmp);
    }
}
