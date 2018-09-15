package pxlgen.core.command;

import pxlgen.core.function.Function;

/**
 * PartialCommand.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
public class PartialCommand extends Command {

    private boolean hasParams = false;

    public PartialCommand(String name) {
        super(name);
    }

    public PartialCommand(String name, String domain) {
        super(name, domain);
    }

    public boolean hasParams() {
        return hasParams;
    }

    public void setHasParams(boolean hasParams) {
        this.hasParams = hasParams;
    }


    public boolean match(Function function) {
        if (getDomain() == null && getName() == null)
            return false;
        boolean domainMatch = true;
        boolean nameMatch = true;
        if (getDomain() != null) {
            if (getName() != null) {
                if (hasParams()) {
                    nameMatch = function.getName().equals(getName());
                } else {
                    nameMatch = function.getName().startsWith(getName());
                }
                domainMatch = function.getDomain().equals(getDomain());
            }
        } else if (!hasParams()) {
            nameMatch = function.getName().contains(getName());
            domainMatch = function.getDomain().startsWith(getName());
            return domainMatch || nameMatch;
        } else {
            nameMatch = function.getName().equals(getName());
        }
        return domainMatch && nameMatch;
    }
}
