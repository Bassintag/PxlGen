package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * Grow.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Grow {

    @Function(description = "Expands all areas by an amount")
    public void grow(ImageBuffer imageBuffer, @Name("amount") float radius) {
        int radiusI = (int) radius;
        ImageBuffer tmp = new ImageBuffer(imageBuffer);
        tmp.eachPixel((c, x, y) -> {
            for (int rx = x - radiusI; rx <= x + radiusI; rx += 1) {
                for (int ry = y - radiusI; ry <= y + radiusI; ry += 1) {
                    if (imageBuffer.getAt(rx, ry) != null)
                        return Color.WHITE;
                }
            }
            return null;
        });
        imageBuffer.setFrom(tmp);
    }

    @Function(description = "Expands all areas")
    public void grow(ImageBuffer imageBuffer) {
        grow(imageBuffer, 1);
    }
}