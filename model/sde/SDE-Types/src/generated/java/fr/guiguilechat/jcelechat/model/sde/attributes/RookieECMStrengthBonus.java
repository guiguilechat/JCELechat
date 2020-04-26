package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ECM Strength Bonus
 */
public class RookieECMStrengthBonus
    extends IntAttribute
{
    public static final RookieECMStrengthBonus INSTANCE = new RookieECMStrengthBonus();

    @Override
    public int getId() {
        return  1828;
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
        return "RookieECMStrengthBonus";
    }
}
