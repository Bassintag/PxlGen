package pxlgen.core.image;

/**
 * Layer.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Layer extends ImageBuffer{

    private final String name;

    public Layer(int width, int height, String name) {
        super(width, height);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
