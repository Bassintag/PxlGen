package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;
import pxlgen.core.util.ColorUtil;

import java.awt.*;

/**
 * Smooth.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Smooth {

    @Function
    public void smooth(ImageBuffer imageBuffer, float threshold, float radius) {
        int radiusI = (int) radius;
        int thresholdI = (int) threshold;
        int size = radiusI * 2 + 1;
        ImageBuffer tmp = imageBuffer.clone();
        tmp.eachPixel((c, x, y) -> {
            int count = 0;
            Color[] colors = new Color[size * size];
            int i = 0;
            for (int rx = x - radiusI; rx <= x + radiusI; rx += 1) {
                for (int ry = y - radiusI; ry <= y + radiusI; ry += 1) {
                    colors[i] = imageBuffer.getAt(rx, ry);
                    if (colors[i] != null)
                        count += 1;
                    i += 1;
                }
            }
            return count >= thresholdI ? ColorUtil.blend(colors) : c;
        });
        imageBuffer.setFrom(tmp);
    }

    @Function
    public void smooth(ImageBuffer imageBuffer, float threshold) {
        smooth(imageBuffer, threshold, 1);
    }

}
