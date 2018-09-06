package pxlgen.core.util;

import java.awt.*;

/**
 * ColorUtil.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public final class ColorUtil {

    public static Color blend(Color[] colors) {
        int nonNull = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        for (Color color : colors) {
            if (color == null)
                continue;
            nonNull += 1;
            r += color.getRed();
            g += color.getGreen();
            b += color.getBlue();
        }
        if (nonNull == 0)
            return null;
        return new Color(r / nonNull, g / nonNull, b / nonNull);
    }

    public static Color blend(Color color1, Color color2) {
        if (color1 == null)
            return color2;
        if (color2 == null)
            return color1;
        int r = (color1.getRed() + color2.getRed()) / 2;
        int g = (color1.getGreen() + color2.getGreen()) / 2;
        int b = (color1.getBlue() + color2.getBlue()) / 2;
        return new Color(r, g, b);
    }
}
