package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to sensor damper effectiveness
 */
public class RookieDampStrengthBonus
    extends IntAttribute
{
    public static final RookieDampStrengthBonus INSTANCE = new RookieDampStrengthBonus();

    @Override
    public int getId() {
        return  1832;
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
        return "RookieDampStrengthBonus";
    }
}
