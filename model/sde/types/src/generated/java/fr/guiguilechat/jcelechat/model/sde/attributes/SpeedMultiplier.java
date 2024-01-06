package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
 */
public class SpeedMultiplier
    extends RealAttribute
{
    public static final SpeedMultiplier INSTANCE = new SpeedMultiplier();

    @Override
    public int getId() {
        return  204;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "SpeedMultiplier";
    }
}
