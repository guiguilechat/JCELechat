package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * special ammo hold capacity
 */
public class SpecialAmmoHoldCapacity
    extends IntAttribute
{
    public static final SpecialAmmoHoldCapacity INSTANCE = new SpecialAmmoHoldCapacity();

    @Override
    public int getId() {
        return  1573;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpecialAmmoHoldCapacity";
    }
}
