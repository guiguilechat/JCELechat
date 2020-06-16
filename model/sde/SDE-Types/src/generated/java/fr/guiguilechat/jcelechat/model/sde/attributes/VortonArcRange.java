package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum distance from the original target from which additional targets can receive arc damage
 */
public class VortonArcRange
    extends IntAttribute
{
    public static final VortonArcRange INSTANCE = new VortonArcRange();

    @Override
    public int getId() {
        return  3036;
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
        return false;
    }

    @Override
    public String toString() {
        return "VortonArcRange";
    }
}
