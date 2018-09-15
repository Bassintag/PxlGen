package pxlgen.gui;

import pxlgen.core.Pxlgen;
import pxlgen.core.command.PartialCommand;
import pxlgen.core.command.parser.PartialCommandParser;
import pxlgen.core.exception.CommandParsingException;
import pxlgen.core.function.Function;
import pxlgen.core.image.ImageBuffer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * ImageComponent.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class ImageComponent extends JComponent {

    private static final int PIXELS_PER_MESSAGE = 24;

    private static final Color BG_COLOR   = new Color(0, 0, 0, 0.5f);
    private static final Color TEXT_COLOR = new Color(1, 1, 1, 0.8f);

    private List<String> messages;
    private Function[]   functions;

    private BufferedImage image;

    private final Pxlgen pxlgen;

    ImageComponent(final Pxlgen pxlgen) {
        this.pxlgen = pxlgen;
        messages = new ArrayList<>();
        messages.add("Contextual help");
        setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        functions = pxlgen.getFunctions().stream().sorted(Comparator.comparing(Function::getName)).toArray(Function[]::new);
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

    public void clearMessages() {
        messages.clear();
    }

    public void addMessage(String message) {
        if (messages.size() < 6) messages.add(message);
    }

    private void addFunction(Function f) {
        StringBuilder sb = new StringBuilder();
        sb.append(f.getDomain());
        sb.append('.');
        sb.append(f.getName());
        sb.append('(');
        Class[] types = f.getParamTypes();
        String[] names = f.getParamNames();
        for (int i = 1; i < types.length; i += 1) {
            if (i > 1) sb.append(", ");
            sb.append(types[i].getSimpleName());
            if (names[i] != null) {
                sb.append(' ');
                sb.append(names[i]);
            }
        }
        sb.append(')');
        String desc = f.getDescription().trim();
        if (desc.length() > 0) {
            sb.append(" - ");
            sb.append(desc);
        }
        addMessage(sb.toString());
    }

    public void addMatchingFunctions(String text) {
        clearMessages();
        Arrays.stream(pxlgen.getMatchingFunction(text)).forEach(this::addFunction);
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
        int height = PIXELS_PER_MESSAGE * messages.size();
        g.setColor(BG_COLOR);
        g.fillRect(2, getHeight() - height - 2, getWidth() - 4, height);
        g.setColor(TEXT_COLOR);
        for (int i = 0; i < messages.size(); i += 1)
            g.drawString(messages.get(messages.size() - i - 1), 4, getHeight() - 8 - PIXELS_PER_MESSAGE * i);
    }
}
