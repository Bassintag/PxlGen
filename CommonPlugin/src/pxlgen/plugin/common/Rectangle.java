package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * Rectangle.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Rectangle {

    @Function
    public void rectangle(ImageBuffer buffer, float startX, float startY, float endX, float endY) {
        buffer.drawRect(Color.WHITE, (int) startX, (int) startY, (int) endX, (int) endY);
    }
}
