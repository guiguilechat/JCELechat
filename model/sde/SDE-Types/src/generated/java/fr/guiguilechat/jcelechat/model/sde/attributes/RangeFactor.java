package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the multiplier/divisor for probe range increases and associated values under the revised probing system
 */
public class RangeFactor
    extends IntAttribute
{
    public static final RangeFactor INSTANCE = new RangeFactor();

    @Override
    public int getId() {
        return  1373;
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
        return "RangeFactor";
    }
}
