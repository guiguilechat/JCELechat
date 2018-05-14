package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The amount of damage done if the crystal is damaged in the process of using it.
 */
public class CrystalVolatilityDamage
    extends DoubleAttribute
{
    public final static CrystalVolatilityDamage INSTANCE = new CrystalVolatilityDamage();

    @Override
    public int getId() {
        return  784;
    }

    @Override
    public int getCatId() {
        return  7;
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
}
