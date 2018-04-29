package pxlgen.plugin.common;

import pxlgen.core.Direction;
import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

/**
 * Nudge.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Nudge {

    @Function
    public void nudge(ImageBuffer imageBuffer, Direction direction, float factor) {
        ImageBuffer tmp = imageBuffer.clone();
        int factorI = (int) factor;
        tmp.eachPixel((c, x, y) -> imageBuffer.getAt(
                x - direction.getRelX() * factorI,
                y - direction.getRelY() * factorI)
        );
        imageBuffer.setFrom(tmp);
    }

    @Function
    public void nudge(ImageBuffer imageBuffer, Direction direction) {
        nudge(imageBuffer, direction, 1);
    }
}
