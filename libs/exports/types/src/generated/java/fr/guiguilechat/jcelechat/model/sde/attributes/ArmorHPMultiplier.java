package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the HP of a ships armor module.
 */
public class ArmorHPMultiplier
    extends RealAttribute
{
    public static final ArmorHPMultiplier INSTANCE = new ArmorHPMultiplier();

    @Override
    public int getId() {
        return  148;
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
        return "ArmorHPMultiplier";
    }
}
