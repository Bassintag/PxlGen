package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;
import java.util.Random;

/**
 * Grid.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Grid {

    private void horLine(ImageBuffer imageBuffer, int x, int y, int width) {
        for (int rx = x; rx < x + width; rx += 1) {
            imageBuffer.setAt(rx, y, Color.WHITE);
        }
    }

    private void vertLine(ImageBuffer imageBuffer, int x, int y, int height) {
        for (int ry = y; ry < y + height; ry += 1) {
            imageBuffer.setAt(x, ry, Color.WHITE);
        }
    }

    @Function(description = "Draws a grid that can be filled based on probabilities")
    public void grid(ImageBuffer imageBuffer,
                     @Name("cellWidth") float widthF,
                     @Name("cellHeight") float heightF,
                     @Name("borderProbability") float borderProb,
                     @Name("fillProbability") float fillProb) {
        int width = (int) widthF;
        int height = (int) heightF;
        if (width <= 0 || height <= 0)
            return;
        Random random = new Random();
        for (int y = 0; y < imageBuffer.getHeight(); y += height) {
            for (int x = 0; x < imageBuffer.getWidth(); x += width) {
                if (random.nextFloat() < borderProb)
                    horLine(imageBuffer, x, y, width + 1);
                if (random.nextFloat() < borderProb)
                    vertLine(imageBuffer, x, y, height + 1);
                if (random.nextFloat() < fillProb)
                    imageBuffer.drawRect(Color.WHITE, x, y, x + width, y + width);
            }
        }
    }

    @Function(description = "Draws a grid")
    public void grid(ImageBuffer imageBuffer,
                     @Name("cellWidth") float widthF,
                     @Name("cellHeight") float heightF) {
        grid(imageBuffer, widthF, heightF, 1, 0);
    }
}
