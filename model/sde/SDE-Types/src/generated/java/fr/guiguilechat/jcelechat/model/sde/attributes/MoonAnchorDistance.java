package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many meters from the standard warp-in distance a moon can be anchored from.
 */
public class MoonAnchorDistance
    extends IntAttribute
{
    public static final MoonAnchorDistance INSTANCE = new MoonAnchorDistance();

    @Override
    public int getId() {
        return  711;
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
        return "MoonAnchorDistance";
    }
}
