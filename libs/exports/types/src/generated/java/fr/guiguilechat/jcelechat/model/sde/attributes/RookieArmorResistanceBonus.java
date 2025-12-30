package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to armor resistances
 */
public class RookieArmorResistanceBonus
    extends IntAttribute
{
    public static final RookieArmorResistanceBonus INSTANCE = new RookieArmorResistanceBonus();

    @Override
    public int getId() {
        return  1825;
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
        return "RookieArmorResistanceBonus";
    }
}
