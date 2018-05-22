package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplier of range the relevant weapon.
 */
public class WeaponRangeMultiplier
    extends DoubleAttribute
{
    public final static WeaponRangeMultiplier INSTANCE = new WeaponRangeMultiplier();

    @Override
    public int getId() {
        return  120;
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
        return "WeaponRangeMultiplier";
    }
}
