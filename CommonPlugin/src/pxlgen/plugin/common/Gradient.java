package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;
import java.util.Random;

/**
 * Gradient.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Gradient {

    @Function
    public void horizontalGradient(ImageBuffer imageBuffer, float noiseFactor) {
        Random random = new Random();
        imageBuffer.eachPixel((c, x, y) -> {
            float relY = ((float) y + 0.5f) / imageBuffer.getHeight();
            float factor = Math.abs(relY - 0.5f) * 2;
            if (random.nextFloat() * noiseFactor > factor)
                return Color.WHITE;
            return c;
        });
    }

    @Function
    public void horizontalGradient(ImageBuffer imageBuffer) {
        horizontalGradient(imageBuffer, 1);
    }

    @Function
    public void verticalGradient(ImageBuffer imageBuffer, float noiseFactor) {
        Random random = new Random();
        imageBuffer.eachPixel((c, x, y) -> {
            float relX = ((float) x + 0.5f) / imageBuffer.getWidth();
            float factor = Math.abs(relX - 0.5f) * 2;
            if (random.nextFloat() * noiseFactor > factor)
                return Color.WHITE;
            return c;
        });
    }

    @Function
    public void verticalGradient(ImageBuffer imageBuffer) {
        verticalGradient(imageBuffer, 1);
    }

    @Function
    public void radialGradient(ImageBuffer imageBuffer, float noiseFactor) {
        Random random = new Random();
        imageBuffer.eachPixel((c, x, y) -> {
            float relX = ((float) x + 0.5f) / imageBuffer.getWidth() - 0.5f;
            float relY = ((float) y + 0.5f) / imageBuffer.getHeight() - 0.5f;
            double factor = Math.sqrt(relX * relX + relY * relY) * 2;
            if (random.nextFloat() * noiseFactor > factor)
                return Color.WHITE;
            return c;
        });
    }

    @Function
    public void radialGradient(ImageBuffer imageBuffer) {
        radialGradient(imageBuffer, 1);
    }
}
