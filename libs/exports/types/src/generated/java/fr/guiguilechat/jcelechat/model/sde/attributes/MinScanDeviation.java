package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Minimum scanning error
 */
public class MinScanDeviation
    extends IntAttribute
{
    public static final MinScanDeviation INSTANCE = new MinScanDeviation();

    @Override
    public int getId() {
        return  787;
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
        return "MinScanDeviation";
    }
}
