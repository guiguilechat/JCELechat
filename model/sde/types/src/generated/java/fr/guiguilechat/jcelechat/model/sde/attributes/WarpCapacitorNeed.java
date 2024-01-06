package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The power cost to warp per one kg per AU (floats do not have the resolution for meters).
 */
public class WarpCapacitorNeed
    extends RealAttribute
{
    public static final WarpCapacitorNeed INSTANCE = new WarpCapacitorNeed();

    @Override
    public int getId() {
        return  153;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "WarpCapacitorNeed";
    }
}
