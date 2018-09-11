package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * paint.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */

@FunctionHandler
public class Fill {

    @Function
    public void fill(ImageBuffer buffer, float red, float green, float blue) {
        Color c = new Color(red, green, blue);
        buffer.eachPixel((color) -> (c));
    }

    private void floodFill(ImageBuffer buffer, int x, int y, Color mask, Color color) {
        if (x < 0 || y < 0 || x >= buffer.getWidth() || y >= buffer.getHeight())
            return;
        if (!buffer.getAt(x, y).equals(mask))
            return;
        buffer.setAt(color, x, y);
        floodFill(buffer, x - 1, y, mask, color);
        floodFill(buffer, x + 1, y, mask, color);
        floodFill(buffer, x, y - 1, mask, color);
        floodFill(buffer, x, y + 1, mask, color);
    }

    @Function
    public void floodFill(ImageBuffer buffer, float x, float y, float red, float green, float blue) {
        int xi = (int) x;
        int yi = (int) y;
        Color mask = buffer.getAt(xi, yi);
        Color color = new Color(red, green, blue);
        floodFill(buffer, xi, yi, mask, color);
    }

    @Function
    public void floodFill(ImageBuffer buffer, float x, float y) {
        floodFill(buffer, x, y, 1, 1, 1);
    }

}
