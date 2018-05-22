package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to Small Projectile Turret damage
 */
public class RookieSPTDamageBonus
    extends IntAttribute
{
    public final static RookieSPTDamageBonus INSTANCE = new RookieSPTDamageBonus();

    @Override
    public int getId() {
        return  1836;
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
        return "RookieSPTDamageBonus";
    }
}
