package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;
import pxlgen.plugin.common.areas.Area;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Largest.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 13/09/2018
 */
@FunctionHandler
public class Largest {

    private void paintAreas(ImageBuffer imageBuffer, List<Area> areas) {
        ImageBuffer buffer = new ImageBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
        areas.forEach(area -> area.paint(imageBuffer, buffer));
        imageBuffer.setFrom(buffer);
    }

    @Function(description = "Clears all the groups except the largest")
    public void largest(ImageBuffer imageBuffer) {
        List<Area> areas = Area.getAreas(imageBuffer);
        if (areas.size() == 0)
            return;
        Area largest = areas.stream().max(Comparator.comparingInt(List::size)).get();
        ImageBuffer buffer = new ImageBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
        largest.paint(imageBuffer, buffer);
        imageBuffer.setFrom(buffer);
    }

    @Function(description = "Clears all the groups bellow a size threshold")
    public void largest(ImageBuffer imageBuffer,
                        @Name("threshold") float threshold) {
        List<Area> areas = Area.getAreas(imageBuffer);
        paintAreas(imageBuffer, areas.stream().filter(a -> a.size() > threshold).collect(Collectors.toList()));

    }
}
