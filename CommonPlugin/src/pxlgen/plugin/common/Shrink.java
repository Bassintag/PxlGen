package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;

/**
 * Shrink.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Shrink {

    @Function(description = "Reduces all areas by an amount")
    public void shrink(ImageBuffer imageBuffer,
                       @Name("amount") float radius) {
        int radiusI = (int) radius;
        ImageBuffer tmp = new ImageBuffer(imageBuffer);
        tmp.eachPixel((c, x, y) -> {
            for (int rx = x - radiusI; rx <= x + radiusI; rx += 1) {
                for (int ry = y - radiusI; ry <= y + radiusI; ry += 1) {
                    if (imageBuffer.getAt(rx, ry) == null)
                        return null;
                }
            }
            return c;
        });
        imageBuffer.setFrom(tmp);
    }

    @Function(description = "Reduces all areas")
    public void shrink(ImageBuffer imageBuffer) {
        shrink(imageBuffer, 1);
    }
}
