package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

/**
 * Mirror.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 13/09/2018
 */
@FunctionHandler
public class Mirror {

    @Function(description = "Mirrors the image horizontally")
    public void horizontalMirror(ImageBuffer imageBuffer) {
        imageBuffer.eachPixel((c, x, y) -> {
            if (x > imageBuffer.getWidth() / 2)
                return imageBuffer.getAt(imageBuffer.getWidth() / 2 - (x - imageBuffer.getWidth() / 2), y);
            return c;
        });
    }

    @Function(description = "Mirrors the image vertically")
    public void verticalMirror(ImageBuffer imageBuffer) {
        imageBuffer.eachPixel((c, x, y) -> {
            if (y > imageBuffer.getHeight() / 2)
                return imageBuffer.getAt(x, imageBuffer.getHeight() / 2 - (y - imageBuffer.getHeight() / 2));
            return c;
        });
    }
}
