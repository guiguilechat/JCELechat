package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier of range the relevant weapon.
 */
public class WeaponRangeMultiplier
    extends RealAttribute
{
    public static final WeaponRangeMultiplier INSTANCE = new WeaponRangeMultiplier();

    @Override
    public int getId() {
        return  120;
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
        return "WeaponRangeMultiplier";
    }
}
