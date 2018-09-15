package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;
import pxlgen.core.util.ColorUtil;
import pxlgen.plugin.common.cellularValidator.ICellularValidator;
import pxlgen.plugin.common.cellularValidator.MooreCellularValidator;
import pxlgen.plugin.common.cellularValidator.VNCellularValidator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Smooth.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Smooth {

    private Map<String, ICellularValidator> validators;

    public Smooth() {
        validators = new HashMap<>();
        validators.put("moore", new MooreCellularValidator());
        validators.put("vn", new VNCellularValidator());
    }

    private ICellularValidator getValidator(String name) {
        return validators.get(name);
    }

    @Function(description = "Smoothes the image n times with a threshold in a radius and using a specified algorithm")
    public void smooth(ImageBuffer imageBuffer,
                       @Name("iterations") float iterations,
                       @Name("threshold") float threshold,
                       @Name("radius") float radius,
                       @Name("algorithm") String algorithm) {
        while (iterations > 0) {
            int radiusI = (int) radius;
            int thresholdI = (int) threshold;
            int size = radiusI * 2 + 1;
            ImageBuffer tmp = imageBuffer.clone();
            ICellularValidator validator = getValidator(algorithm);
            tmp.eachPixel((c, x, y) -> {
                int count = 0;
                Color[] colors = new Color[size * size];
                int i = 0;
                for (int rx = x - radiusI; rx <= x + radiusI; rx += 1) {
                    for (int ry = y - radiusI; ry <= y + radiusI; ry += 1) {
                        if (!validator.isValid(x, y, rx, ry, radiusI)) continue;
                        colors[i] = imageBuffer.getAt(rx, ry);
                        if (colors[i] != null)
                            count += 1;
                        i += 1;
                    }
                }
                return count >= thresholdI ? ColorUtil.blend(colors) : null;
            });
            imageBuffer.setFrom(tmp);
            iterations -= 1;
        }
    }

    @Function(description = "Smoothes the image n times using a threshold and a specified algorithm")
    public void smooth(ImageBuffer imageBuffer,
                       @Name("iterations") float iterations,
                       @Name("threshold") float threshold,
                       @Name("algorithm") String algorithm) {
        smooth(imageBuffer, iterations, threshold, 1, algorithm);
    }

    @Function(description = "Smoothes the image n times using a threshold")
    public void smooth(ImageBuffer imageBuffer,
                       @Name("iterations") float iterations,
                       @Name("threshold") float threshold) {
        smooth(imageBuffer, iterations, threshold, "moore");
    }

    @Function(description = "Smoothes the image using a threshold")
    public void smooth(ImageBuffer imageBuffer,
                       @Name("threshold") float threshold) {
        smooth(imageBuffer, 1, threshold);
    }

}
