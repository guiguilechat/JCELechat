package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to Small Projectile Turret damage
 */
public class RookieSPTDamageBonus
    extends IntAttribute
{
    public static final RookieSPTDamageBonus INSTANCE = new RookieSPTDamageBonus();

    @Override
    public int getId() {
        return  1836;
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
