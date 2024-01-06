package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * This is the lowest scan range value in AUs for probes under the revised probing system
 */
public class BaseScanRange
    extends RealAttribute
{
    public static final BaseScanRange INSTANCE = new BaseScanRange();

    @Override
    public int getId() {
        return  1370;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return true;
    }

    @Override
    public String toString() {
        return "BaseScanRange";
    }
}
