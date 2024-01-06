package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The factor by which the structure modifies the using pilot's refining yield rate.
 */
public class RefiningYieldMultiplier
    extends RealAttribute
{
    public static final RefiningYieldMultiplier INSTANCE = new RefiningYieldMultiplier();

    @Override
    public int getId() {
        return  717;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.5;
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
        return "RefiningYieldMultiplier";
    }
}
