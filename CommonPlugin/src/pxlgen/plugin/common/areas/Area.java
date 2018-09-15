package pxlgen.plugin.common.areas;

import pxlgen.core.image.ImageBuffer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Area.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
public class Area extends ArrayList<Point> {

    public void paint(ImageBuffer source, ImageBuffer dest) {
        for (Point p : this) {
            dest.setAt(p.x, p.y, source.getAt(p.x, p.y));
        }
    }

    public void paint(ImageBuffer imageBuffer, Color color) {
        for (Point p : this) {
            imageBuffer.setAt(p.x, p.y, color);
        }
    }

    private static void getPoints(ImageBuffer imageBuffer, List<Point> points, int x, int y) {
        Point p = new Point(x, y);
        if (imageBuffer.getAt(x, y) == null || points.contains(p))
            return;
        points.add(p);
        getPoints(imageBuffer, points, x - 1, y);
        getPoints(imageBuffer, points, x + 1, y);
        getPoints(imageBuffer, points, x, y - 1);
        getPoints(imageBuffer, points, x, y + 1);
    }

    public static List<Area> getAreas(ImageBuffer imageBuffer) {
        java.util.List<Area> areas = new ArrayList<>();
        List<Point> used = new ArrayList<>();
        for (int x = 0; x < imageBuffer.getWidth(); x += 1) {
            for (int y = 0; y < imageBuffer.getHeight(); y += 1) {
                if (used.contains(new Point(x, y)))
                    continue;
                Area area = new Area();
                getPoints(imageBuffer, area, x, y);
                if (area.size() > 0) {
                    used.addAll(area);
                    areas.add(area);
                }
            }
        }
        return areas;
    }
}
