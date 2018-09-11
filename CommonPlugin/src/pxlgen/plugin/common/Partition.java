package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;
import pxlgen.plugin.common.bsp.Leaf;
import pxlgen.plugin.common.bsp.Partitioner;

import java.awt.*;
import java.util.Random;

/**
 * Partition.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
@FunctionHandler
public class Partition {

    @Function
    public void partition(ImageBuffer imageBuffer, float minSize, float maxSize, float minRectSizeF, float maxRectSizeF) {
        Partitioner partitioner = new Partitioner(imageBuffer.getWidth(), imageBuffer.getHeight(), (int) minSize, (int) maxSize);
        Leaf[] leaves = partitioner.partition();
        Random random = new Random();
        int minRectSize = (int) minRectSizeF;
        int maxRectSize = (int) maxRectSizeF;
        for (Leaf l : leaves) {
            int min = minRectSize;
            if (min <= 0)
                min = l.getWidth() + min;
            int max = maxRectSize;
            if (max <= 0)
                max = l.getWidth() + max;
            int width = min;
            if (min != max)
                    width += random.nextInt(max - min);
            min = minRectSize;
            if (min <= 0)
                min = l.getHeight() + min;
            max = maxRectSize;
            if (max <= 0)
                max = l.getHeight() + max;
            int height = min;
            if (min != max)
                height += random.nextInt(max - min);
            int x = l.getX() + (l.getWidth() - width) / 2;
            int y = l.getY() + (l.getHeight() - height) / 2;
            imageBuffer.drawRect(Color.WHITE, x, y, x + width, y + height);
        }
    }

    @Function
    public void partition(ImageBuffer imageBuffer, float minSize, float maxSize) {
        partition(imageBuffer, minSize, maxSize, 0, 0);
    }
}
