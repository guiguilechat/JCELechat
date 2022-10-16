package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to tracking disruptor effectiveness
 */
public class RookieWeaponDisruptionBonus
    extends IntAttribute
{
    public static final RookieWeaponDisruptionBonus INSTANCE = new RookieWeaponDisruptionBonus();

    @Override
    public int getId() {
        return  1824;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RookieWeaponDisruptionBonus";
    }
}
