package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * scanning speed in milliseconds
 */
public class ScanSpeed
    extends IntAttribute
{
    public static final ScanSpeed INSTANCE = new ScanSpeed();

    @Override
    public int getId() {
        return  79;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "ScanSpeed";
    }
}
