package pxlgen.gui;

import pxlgen.core.Pxlgen;
import pxlgen.core.image.ImageBuffer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ImageComponent.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class ImageComponent extends JComponent {

    private BufferedImage image;

    private final Pxlgen pxlgen;

    ImageComponent(final Pxlgen pxlgen) {
        this.pxlgen = pxlgen;
    }

    public void refreshImage() {
        ImageBuffer buffer = pxlgen.getImage();
        image = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < buffer.getWidth(); x += 1) {
            for (int y = 0; y < buffer.getHeight(); y += 1) {
                Color color = buffer.getAt(x, y);
                if (color == null)
                    color = Color.BLACK;
                image.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double ratio = Math.min(((double) getWidth()) / image.getWidth(), ((double) getHeight()) / image.getHeight());
        double mX = getWidth() - image.getWidth() * ratio;
        double mY = getHeight() - image.getHeight() * ratio;
        g.drawImage(image, (int) (mX / 2), (int) (mY / 2),
                (int) (image.getWidth() * ratio),
                (int) (image.getHeight() * ratio),
                Color.BLACK, null);
    }
}
