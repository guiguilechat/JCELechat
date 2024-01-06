package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Base cycle time (in seconds) of an extractor pin.
 */
public class PinCycleTime
    extends IntAttribute
{
    public static final PinCycleTime INSTANCE = new PinCycleTime();

    @Override
    public int getId() {
        return  1643;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
