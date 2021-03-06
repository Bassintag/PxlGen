package pxlgen.core.image;

import java.awt.*;

/**
 * ImageBuffer.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class ImageBuffer {

    private int width;
    private int height;

    private Color[] buffer;

    public ImageBuffer(int width, int height) {
        resize(width, height);
    }

    public ImageBuffer(int width, int height, Color[] buffer) {
        this(width, height);
        int len = Math.min(this.buffer.length, buffer.length);
        System.arraycopy(buffer, 0, this.buffer, 0, len);
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        buffer = new Color[width * height];
    }

    public ImageBuffer(ImageBuffer imageBuffer) {
        this(imageBuffer.width, imageBuffer.height, imageBuffer.buffer);
    }

    public void setFrom(ImageBuffer buffer) {
        resize(buffer.getWidth(), buffer.getHeight());
        eachPixel(((color, x, y) -> (buffer.getAt(x, y))));
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public Color getAt(int x, int y) {
        if (!isInBounds(x, y))
            return null;
        return buffer[y * width + x];
    }

    public Color sampleAt(float x, float y) {
        return getAt((int) (x * width), (int) (y * height));
    }

    public void setAt(int x, int y, Color color) {
        if (isInBounds(x, y))
            buffer[y * width + x] = color;
    }

    public void setAt(int x, int y, boolean b) {
        if (isInBounds(x, y))
            buffer[y * width + x] = b ? new Color(1f, 1f, 1f) : null;
    }

    public void drawBorderRect(Color color, int startX, int startY, int endX, int endY) {
        for (int y = startY; y < endY; y += 1) {
            setAt(startX, y, color);
            setAt(endX - 1, y, color);
        }
        for (int x = startX; x < endX; x += 1) {
            setAt(x, startY, color);
            setAt(x, endY - 1, color);
        }
    }

    public void drawRect(Color color, int startX, int startY, int endX, int endY) {
        for (int y = startY; y < endY; y += 1) {
            for (int x = startX; x < endX; x += 1) {
                setAt(x, y, color);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public interface PixelHandler {
        Color handle(Color color);
    }

    public int eachPixel(PixelHandler handler) {
        return eachPixel(handler, true);
    }

    public int eachPixel(PixelHandler handler, boolean passNulls) {
        int count = 0;
        for (int i = 0; i < buffer.length; i += 1) {
            if (passNulls || buffer[i] != null) {
                Color ret = handler.handle(buffer[i]);
                if (ret != buffer[i] && (ret == null || !ret.equals(buffer[i])))
                    count += 1;
                buffer[i] = ret;
            }
        }
        return count;
    }

    public interface PositionPixelHandler {
        Color handle(Color color, int x, int y);
    }

    public int eachPixel(PositionPixelHandler handler) {
        return eachPixel(handler, true);
    }

    public int eachPixel(PositionPixelHandler handler, boolean passNulls) {
        int count = 0;
        for (int y = 0; y < height; y += 1) {
            for (int x = 0; x < width; x += 1) {
                Color color = getAt(x, y);
                if (passNulls || color != null) {
                    Color ret = handler.handle(color, x, y);
                    if (ret != color && (ret == null || !ret.equals(color)))
                        count += 1;
                    setAt(x, y, ret);
                }
            }
        }
        return count;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ImageBuffer clone() {
        return new ImageBuffer(this);
    }

    public boolean[] toBinaryBuffer() {
        boolean[] ret = new boolean[buffer.length];
        for (int i = 0; i < buffer.length; i += 1) {
            ret[i] = buffer[i] != null;
        }
        return ret;
    }
}
