package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to tracking disruptor effectiveness
 */
public class RookieWeaponDisruptionBonus
    extends IntAttribute
{
    public final static RookieWeaponDisruptionBonus INSTANCE = new RookieWeaponDisruptionBonus();

    @Override
    public int getId() {
        return  1824;
    }

    @Override
    public int getCatId() {
        return  0;
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
