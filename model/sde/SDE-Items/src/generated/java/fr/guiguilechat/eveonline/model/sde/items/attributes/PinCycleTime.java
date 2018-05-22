package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Base cycle time (in seconds) of an extractor pin.
 */
public class PinCycleTime
    extends IntAttribute
{
    public final static PinCycleTime INSTANCE = new PinCycleTime();

    @Override
    public int getId() {
        return  1643;
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
        return  300.0;
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
        return "PinCycleTime";
    }
}
