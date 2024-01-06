package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * This is the lowest maximum scan deviation in AU for probes under the revised probing system
 */
public class BaseMaxScanDeviation
    extends RealAttribute
{
    public static final BaseMaxScanDeviation INSTANCE = new BaseMaxScanDeviation();

    @Override
    public int getId() {
        return  1372;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "BaseMaxScanDeviation";
    }
}
