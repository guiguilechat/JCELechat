package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * This is the lowest scan range value in AUs for probes under the revised probing system
 */
public class BaseScanRange
    extends DoubleAttribute
{
    public final static BaseScanRange INSTANCE = new BaseScanRange();

    @Override
    public int getId() {
        return  1370;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
