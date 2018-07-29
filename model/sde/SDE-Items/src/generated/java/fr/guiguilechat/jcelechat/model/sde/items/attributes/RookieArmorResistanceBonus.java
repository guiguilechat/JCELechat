package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to armor resistances
 */
public class RookieArmorResistanceBonus
    extends IntAttribute
{
    public final static RookieArmorResistanceBonus INSTANCE = new RookieArmorResistanceBonus();

    @Override
    public int getId() {
        return  1825;
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
        return "RookieArmorResistanceBonus";
    }
}
