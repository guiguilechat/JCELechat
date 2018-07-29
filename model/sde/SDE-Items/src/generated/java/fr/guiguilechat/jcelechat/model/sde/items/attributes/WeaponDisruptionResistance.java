package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Resistance against Remote Weapon Disruptors.
 */
public class WeaponDisruptionResistance
    extends DoubleAttribute
{
    public final static WeaponDisruptionResistance INSTANCE = new WeaponDisruptionResistance();

    @Override
    public int getId() {
        return  2113;
    }

    @Override
    public int getCatId() {
        return  36;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "WeaponDisruptionResistance";
    }
}
