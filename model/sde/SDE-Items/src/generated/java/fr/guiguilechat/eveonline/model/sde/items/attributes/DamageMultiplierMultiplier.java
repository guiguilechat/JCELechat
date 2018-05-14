package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplys the damage multiplier
 */
public class DamageMultiplierMultiplier
    extends DoubleAttribute
{
    public final static DamageMultiplierMultiplier INSTANCE = new DamageMultiplierMultiplier();

    @Override
    public int getId() {
        return  1482;
    }

    @Override
    public int getCatId() {
        return  1;
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
