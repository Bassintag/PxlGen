package pxlgen.plugin.common.cellularValidator;

/**
 * VNCellularValidator.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
public class VNCellularValidator implements ICellularValidator {

    @Override
    public boolean isValid(int x, int y, int x1, int y1, int radius) {
        return (x == x1 || y == y1) && Math.abs(x - x1) <= radius && Math.abs(y - y1) <= radius;
    }
}
