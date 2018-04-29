package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
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
            imageBuffer.setAt(Color.WHITE, rx, y);
        }
    }

    private void vertLine(ImageBuffer imageBuffer, int x, int y, int height) {
        for (int ry = y; ry < y + height; ry += 1) {
            imageBuffer.setAt(Color.WHITE, x, ry);
        }
    }

    @Function
    public void grid(ImageBuffer imageBuffer, float widthF, float heightF, float borderProb, float fillProb) {
        int width = (int) widthF;
        int height = (int) heightF;
        Random random = new Random();
        for (int y = 0; y < imageBuffer.getHeight(); y += height - 1) {
            for (int x = 0; x < imageBuffer.getWidth(); x += width - 1) {
                if (random.nextFloat() < borderProb)
                    horLine(imageBuffer, x, y, width);
                if (random.nextFloat() < borderProb)
                    vertLine(imageBuffer, x, y, height);
                if (random.nextFloat() < fillProb)
                    imageBuffer.drawRect(Color.WHITE, x, y, x + width, y + width);
            }
        }
    }

    @Function
    public void grid(ImageBuffer imageBuffer, float widthF, float heightF) {
        grid(imageBuffer, widthF, heightF, 0, 0);
    }
}
