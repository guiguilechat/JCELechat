package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to the HP of a ships armor module.
 */
public class ArmorHPMultiplier
    extends DoubleAttribute
{
    public static final ArmorHPMultiplier INSTANCE = new ArmorHPMultiplier();

    @Override
    public int getId() {
        return  148;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return "ArmorHPMultiplier";
    }
}
