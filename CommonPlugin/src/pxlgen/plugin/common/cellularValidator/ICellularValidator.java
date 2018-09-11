package pxlgen.plugin.common.cellularValidator;

/**
 * ICellularValidator.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
public interface ICellularValidator {

    boolean isValid(int x, int y, int x1, int y1, int radius);
}
