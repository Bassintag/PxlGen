package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
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
                dest.setAt(c, destX, destY);
            return c;
        });
    }

    @Function
    public void resize(ImageBuffer imageBuffer, float widthF, float heightF, boolean stretch) {
        int width = (int) widthF;
        int height = (int) heightF;
        ImageBuffer buffer = new ImageBuffer(width, height);
        if (stretch)
            resizeStretch(imageBuffer, buffer);
        else
            resizeCenter(imageBuffer, buffer);
        imageBuffer.setFrom(buffer);
    }

    @Function
    public void resize(ImageBuffer imageBuffer, float width, float height) {
        resize(imageBuffer, width, height, true);
    }
}
