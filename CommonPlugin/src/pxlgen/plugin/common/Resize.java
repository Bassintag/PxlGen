package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;

/**
 * Resize.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
@FunctionHandler
public class Resize {

    private void resizeStretch(ImageBuffer source, ImageBuffer dest)
    {
        float rw = ((float) (source.getWidth())) / dest.getWidth();
        float rh = ((float) (source.getHeight())) / dest.getHeight();
        dest.eachPixel((c, x, y) -> source.getAt((int) (x * rw), (int) (y * rh)));
    }

    private void resizeCenter(ImageBuffer source, ImageBuffer dest)
    {
        int minX = (dest.getWidth() - source.getWidth()) / 2;
        int minY = (dest.getHeight() - source.getHeight()) / 2;
        source.eachPixel((c, x, y) -> {
            int destX = minX + x;
            int destY = minY + y;
            if (destX >= 0 && destX < dest.getWidth())
                dest.setAt(destX, destY, c);
            return c;
        });
    }

    @Function(description = "Resizes the image by stretching or not")
    public void resize(ImageBuffer imageBuffer,
                       @Name("width") float widthF,
                       @Name("height") float heightF,
                       @Name("streatch") boolean stretch) {
        int width = (int) widthF;
        int height = (int) heightF;
        ImageBuffer buffer = new ImageBuffer(width, height);
        if (stretch)
            resizeStretch(imageBuffer, buffer);
        else
            resizeCenter(imageBuffer, buffer);
        imageBuffer.setFrom(buffer);
    }

    @Function(description = "Resize the image by stretching")
    public void resize(ImageBuffer imageBuffer,
                       @Name("width") float width,
                       @Name("Height") float height) {
        resize(imageBuffer, width, height, true);
    }
}
