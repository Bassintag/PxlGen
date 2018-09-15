package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
import pxlgen.core.image.ImageBuffer;
import pxlgen.core.util.Pair;
import pxlgen.plugin.common.areas.Area;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tunnel.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
@FunctionHandler
public class Tunnel {

    private void connect(ImageBuffer imageBuffer, Point p1, Point p2) {
        int startX = Math.min(p1.x, p2.x);
        int endX = Math.max(p1.x, p2.x);
        int startY = Math.min(p1.y, p2.y);
        int endY = Math.max(p1.y, p2.y);
        for (int x = startX; x <= endX; x += 1) {
            for (int y = startY; y <= endY; y += 1) {
                imageBuffer.setAt(x, y, true);
            }
        }
    }

    private Pair<Point, Point> canConnect(ImageBuffer imageBuffer, Area area, Point p, float distance) {
        for (Point ap : area) {
            if ((ap.x != p.x && ap.y != p.y) || ap.distance(p) > distance) {
                continue;
            }
            return new Pair<>(p, ap);
        }
        return null;
    }

    private void connectArea(ImageBuffer imageBuffer, Area area, List<Area> others, float distance) {
        for (Area other : others) {
            List<Pair<Point, Point>> valid = new ArrayList<>();
            for (Point point : other) {
                Pair<Point, Point> pointPointPair = canConnect(imageBuffer, area, point, distance);
                if (pointPointPair != null) {
                    valid.add(pointPointPair);
                }
            }
            if (valid.size() > 0) {
                Pair<Point, Point> p = valid.get(new Random().nextInt(valid.size()));
                connect(imageBuffer, p.getKey(), p.getValue());
            }
        }
    }

    @Function(description = "Connects all aligned areas at a maximum distance")
    public void tunnel(ImageBuffer imageBuffer,
                       @Name("maxDistance") float distance) {
        List<Area> areas = Area.getAreas(imageBuffer);
        while (areas.size() > 0) {
            Area area = areas.get(0);
            areas.remove(0);
            connectArea(imageBuffer, area, areas, distance);
        }
    }
}
