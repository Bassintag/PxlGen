package pxlgen.plugin.common.bsp;

import java.util.Random;

/**
 * Leaf.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
public class Leaf {

    private final int minSize;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private Leaf left;
    private Leaf right;

    public Leaf(int x, int y, int width, int height, int minSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minSize = minSize;
    }

    public boolean hasSplit() {
        return left != null || right != null;
    }

    public boolean split() {
        if (hasSplit())
            return false;
        boolean splitH;
        if (width > height && width >= 1.25 * height)
            splitH = false;
        else if (height > width && height >= 1.25 * width)
            splitH = true;
        else
            splitH = new Random().nextFloat() < 0.5;
        int max = (splitH ? height : width) - minSize;
        if (max <= minSize)
            return false;
        int split = new Random().nextInt(max - minSize) + minSize;
        if (splitH) {
            left = new Leaf(x, y, width, split, minSize);
            right = new Leaf(x, y + split, width, height - split, minSize);
        } else {
            left = new Leaf(x, y, split, height, minSize);
            right = new Leaf(x + split, y, width - split, height, minSize);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Leaf<x:" + x + ", y:" + y + ", width:" + width + ", height:" + height + ">";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Leaf getLeft() {
        return left;
    }

    public Leaf getRight() {
        return right;
    }
}
