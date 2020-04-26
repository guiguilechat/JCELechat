package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Scales the range at which something can reach.
 */
public class MaxRangeMultiplier
    extends IntAttribute
{
    public static final MaxRangeMultiplier INSTANCE = new MaxRangeMultiplier();

    @Override
    public int getId() {
        return  243;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "MaxRangeMultiplier";
    }
}
