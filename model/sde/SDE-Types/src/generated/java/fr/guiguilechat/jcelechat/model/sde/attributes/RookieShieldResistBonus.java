package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Shield resistance bonus
 */
public class RookieShieldResistBonus
    extends IntAttribute
{
    public static final RookieShieldResistBonus INSTANCE = new RookieShieldResistBonus();

    @Override
    public int getId() {
        return  1829;
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
        return "RookieShieldResistBonus";
    }
}
