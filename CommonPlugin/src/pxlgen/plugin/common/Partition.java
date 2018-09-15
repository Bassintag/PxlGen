package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
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

    @Function(description = "Partitions the image in varying spaces and fills them")
    public void partition(ImageBuffer imageBuffer,
                          @Name("minSize") float minSize,
                          @Name("maxSize") float maxSize,
                          @Name("minRectSize") float minRectSizeF,
                          @Name("maxRectSize") float maxRectSizeF,
                          @Name("rectProbability") float prob) {
        Partitioner partitioner = new Partitioner(imageBuffer.getWidth(), imageBuffer.getHeight(), (int) minSize, (int) maxSize);
        Leaf[] leaves = partitioner.partition();
        Random random = new Random();
        int minRectSize = (int) minRectSizeF;
        int maxRectSize = (int) maxRectSizeF;
        for (Leaf l : leaves) {
            if (random.nextFloat() > prob) continue;
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
            if (x == 0){
                x += 1;
                width -= 1;
            }
            if (y == 0){
                y += 1;
                height -= 1;
            }
            imageBuffer.drawRect(Color.WHITE, x, y, x + width - 1, y + height - 1);
        }
    }

    @Function(description = "Partitions the image in varying spaces")
    public void partition(ImageBuffer imageBuffer,
                          @Name("minSize") float minSize,
                          @Name("maxSize") float maxSize) {
        partition(imageBuffer, minSize, maxSize, 0, 0, 1);
    }
}
