package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Resistance against Remote Weapon Disruptors.
 */
public class WeaponDisruptionResistance
    extends RealAttribute
{
    public static final WeaponDisruptionResistance INSTANCE = new WeaponDisruptionResistance();

    @Override
    public int getId() {
        return  2113;
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
        return "WeaponDisruptionResistance";
    }
}
