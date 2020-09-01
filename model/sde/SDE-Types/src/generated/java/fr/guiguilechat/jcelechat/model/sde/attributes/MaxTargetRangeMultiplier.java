package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Scales the max target range of a ships electronics.
 */
public class MaxTargetRangeMultiplier
    extends RealAttribute
{
    public static final MaxTargetRangeMultiplier INSTANCE = new MaxTargetRangeMultiplier();

    @Override
    public int getId() {
        return  237;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
