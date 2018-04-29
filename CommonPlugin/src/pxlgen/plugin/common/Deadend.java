package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

/**
 * Deadend.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Deadend {

    @Function
    public void deadend(ImageBuffer imageBuffer) {
        int changed;
        do {
            changed = imageBuffer.eachPixel((c, x, y) -> {
                int count = 0;
                count += (imageBuffer.getAt(x - 1, y) != null) ? 1 : 0;
                count += (imageBuffer.getAt(x + 1, y) != null) ? 1 : 0;
                count += (imageBuffer.getAt(x, y - 1) != null) ? 1 : 0;
                count += (imageBuffer.getAt(x, y + 1) != null) ? 1 : 0;
                if (count <= 1)
                    return null;
                return c;
            });
        } while (changed > 0);
    }
}
