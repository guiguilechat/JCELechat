package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Scales the range at which something can reach.
 */
public class MaxRangeMultiplier
    extends IntAttribute
{
    public final static MaxRangeMultiplier INSTANCE = new MaxRangeMultiplier();

    @Override
    public int getId() {
        return  243;
    }

    @Override
    public int getCatId() {
        return  26;
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
