package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus to shield booster repair amount
 */
public class RookieShieldBoostBonus
    extends DoubleAttribute
{
    public static final RookieShieldBoostBonus INSTANCE = new RookieShieldBoostBonus();

    @Override
    public int getId() {
        return  1837;
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
        return "RookieShieldBoostBonus";
    }
}
