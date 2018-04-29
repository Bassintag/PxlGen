package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.image.ImageBuffer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Layer.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
@FunctionHandler
public class Layer {

    private Map<String, ImageBuffer> layers;

    public Layer() {
        layers = new HashMap<>();
    }

    @Function
    public void saveLayer(ImageBuffer buffer, String name) {
        if (layers.containsKey(name))
            layers.remove(name);
        layers.put(name, buffer.clone());
    }

    @Function
    public void paintLayer(ImageBuffer buffer, String name) {
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> {
                Color color = layer.getAt(x, y);
                return (color == null ? c : color);
            });
        }
    }

    @Function
    public void paintLayer(ImageBuffer buffer, String name, float red, float green, float blue) {
        Color color = new Color(red, green, blue);
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> (layer.getAt(x, y) == null ? c : color));
        }
    }

    @Function
    public void multiplyLayer(ImageBuffer buffer, String name) {
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> {
                Color color = layer.getAt(x, y);
                return (color == null ? null : c);
            });
        }
    }
}
