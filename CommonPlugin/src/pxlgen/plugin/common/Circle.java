package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;

/**
 * Circle.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Circle {

    @Function
    public void circle(ImageBuffer imageBuffer, float x, float y, float radius) {
        imageBuffer.eachPixel((c, pX, pY) -> {
            float rX = (pX + 0.5f) - x;
            float rY = (pY + 0.5f) - y;
            float dist = rX * rX + rY * rY;
            if (dist > radius * radius)
                return c;
            return (Color.WHITE);
        });
    }

}
