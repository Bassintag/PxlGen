package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.util.Random;

/**
 * Divide.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 13/09/2018
 */
@SuppressWarnings("Duplicates")
@FunctionHandler
public class Divide {

    @Function
    public void divide(ImageBuffer buffer, float horP, float vertP, float passes) {
        Random random = new Random();
        while (passes > 0) {
            passes -= 1;
        }
    }

    @Function
    public void divide(ImageBuffer buffer, float horP, float vertP) {
        divide(buffer, horP, vertP, 1);
    }
}
