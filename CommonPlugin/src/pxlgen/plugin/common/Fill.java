package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
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

    @Function(description = "Fills the image with a color")
    public void fill(ImageBuffer buffer,
                     @Name("red") float red,
                     @Name("green") float green,
                     @Name("blue") float blue) {
        Color c = new Color(red, green, blue);
        buffer.eachPixel((color) -> (c));
    }

    private void floodfill(ImageBuffer buffer, int x, int y, Color mask, Color color) {
        if (x < 0 || y < 0 || x >= buffer.getWidth() || y >= buffer.getHeight())
            return;
        Color sample = buffer.getAt(x, y);
        if (sample == null || mask == null) {
            if (sample != mask)
                return;
        } else if (!mask.equals(sample)) {
            return;
        }
        buffer.setAt(x, y, color);
        floodfill(buffer, x - 1, y, mask, color);
        floodfill(buffer, x + 1, y, mask, color);
        floodfill(buffer, x, y - 1, mask, color);
        floodfill(buffer, x, y + 1, mask, color);
    }

    @Function(description = "Floodfills the image with a color")
    public void floodfill(ImageBuffer buffer,
                          @Name("x") float x,
                          @Name("y") float y,
                          @Name("red") float red,
                          @Name("green") float green,
                          @Name("blue") float blue) {
        int xi = (int) x;
        int yi = (int) y;
        Color mask = buffer.getAt(xi, yi);
        Color color = new Color(red, green, blue);
        if (color.equals(buffer.getAt(xi, yi)))
            return;
        floodfill(buffer, xi, yi, mask, color);
    }

    @Function(description = "Floodfills the image")
    public void floodfill(ImageBuffer buffer,
                          @Name("x") float x,
                          @Name("y") float y) {
        floodfill(buffer, x, y, 1, 1, 1);
    }

}
