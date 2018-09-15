package pxlgen.plugin.common;

import pxlgen.core.annotation.Function;
import pxlgen.core.annotation.FunctionHandler;
import pxlgen.core.annotation.Name;
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

    @Function(description = "Saves a layer with a name")
    public void saveLayer(ImageBuffer buffer,
                          @Name("layerName") String name) {
        if (layers.containsKey(name))
            layers.remove(name);
        layers.put(name, buffer.clone());
    }

    @Function(description = "Paint a layer")
    public void paintLayer(ImageBuffer buffer,
                           @Name("layerName") String name) {
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> {
                Color color = layer.getAt(x, y);
                return (color == null ? c : color);
            });
        }
    }

    @Function(description = "Paint a layer with a color")
    public void paintLayer(ImageBuffer buffer,
                           @Name("layerName") String name,
                           @Name("red") float red,
                           @Name("green") float green,
                           @Name("blue") float blue) {
        Color color = new Color(red, green, blue);
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> (layer.getAt(x, y) == null ? c : color));
        }
    }

    @Function(description = "Multiply a layer")
    public void multiplyLayer(ImageBuffer buffer,
                              @Name("layerName") String name) {
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> {
                Color color = layer.getAt(x, y);
                return (color == null ? null : c);
            });
        }
    }

    @Function(description = "Substract a layer")
    public void substractLayer(ImageBuffer buffer,
                               @Name("layerName") String name) {
        if (layers.containsKey(name)) {
            ImageBuffer layer = layers.get(name);
            buffer.eachPixel((c, x, y) -> {
                Color color = layer.getAt(x, y);
                return color == null ? c : null;
            });
        }
    }
}
