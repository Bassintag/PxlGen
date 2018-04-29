package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;
import java.util.Random;

/**
 * Noise.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Noise {

    @Function
    public void noise(ImageBuffer imageBuffer, float probability) {
        Random random = new Random();
        imageBuffer.eachPixel((c) -> {
            if (c == null || random.nextFloat() < probability)
                return null;
            return c;
        });
    }

    @Function
    public void noise(ImageBuffer imageBuffer, float probability, float r, float g, float b) {
        Random random = new Random();
        Color color = new Color(r, g, b);
        imageBuffer.eachPixel((c) -> {
            if (random.nextFloat() < probability)
                return color;
            return c;
        });
    }
}
