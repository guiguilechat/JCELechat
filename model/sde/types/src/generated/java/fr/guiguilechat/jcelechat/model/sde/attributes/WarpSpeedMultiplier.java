package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class WarpSpeedMultiplier
    extends RealAttribute
{
    public static final WarpSpeedMultiplier INSTANCE = new WarpSpeedMultiplier();

    @Override
    public int getId() {
        return  600;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  3.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "WarpSpeedMultiplier";
    }
}
