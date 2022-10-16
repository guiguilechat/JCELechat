package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The amount of damage done if the crystal is damaged in the process of using it.
 */
public class CrystalVolatilityDamage
    extends RealAttribute
{
    public static final CrystalVolatilityDamage INSTANCE = new CrystalVolatilityDamage();

    @Override
    public int getId() {
        return  784;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CrystalVolatilityDamage";
    }
}
