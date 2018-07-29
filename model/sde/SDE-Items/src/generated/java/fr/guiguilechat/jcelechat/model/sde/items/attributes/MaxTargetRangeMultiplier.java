package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Scales the max target range of a ships electronics.
 */
public class MaxTargetRangeMultiplier
    extends DoubleAttribute
{
    public final static MaxTargetRangeMultiplier INSTANCE = new MaxTargetRangeMultiplier();

    @Override
    public int getId() {
        return  237;
    }

    @Override
    public int getCatId() {
        return  24;
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
        return "MaxTargetRangeMultiplier";
    }
}
