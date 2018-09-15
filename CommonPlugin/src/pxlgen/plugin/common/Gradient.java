package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
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

    @Function(description = "Draws a centered horizontal gradient with noise controlled by a factor")
    public void horizontalGradient(ImageBuffer imageBuffer, @Name("factor") float noiseFactor) {
        Random random = new Random();
        imageBuffer.eachPixel((c, x, y) -> {
            float relY = ((float) y + 0.5f) / imageBuffer.getHeight();
            float factor = Math.abs(relY - 0.5f) * 2;
            if (random.nextFloat() * noiseFactor > factor)
                return Color.WHITE;
            return c;
        });
    }

    @Function(description = "Draws a centered horizontal gradient with noise")
    public void horizontalGradient(ImageBuffer imageBuffer) {
        horizontalGradient(imageBuffer, 1);
    }

    @Function(description = "Draws a centered vertical gradient with noise controlled by a factor")
    public void verticalGradient(ImageBuffer imageBuffer, @Name("factor") float noiseFactor) {
        Random random = new Random();
        imageBuffer.eachPixel((c, x, y) -> {
            float relX = ((float) x + 0.5f) / imageBuffer.getWidth();
            float factor = Math.abs(relX - 0.5f) * 2;
            if (random.nextFloat() * noiseFactor > factor)
                return Color.WHITE;
            return c;
        });
    }

    @Function(description = "Draws a centered vertical gradient with noise")
    public void verticalGradient(ImageBuffer imageBuffer) {
        verticalGradient(imageBuffer, 1);
    }

    @Function(description = "Draws a centered radial gradient with noise controlled by a factor")
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

    @Function(description = "Draws a centered radial gradient with noise")
    public void radialGradient(ImageBuffer imageBuffer) {
        radialGradient(imageBuffer, 1);
    }
}
