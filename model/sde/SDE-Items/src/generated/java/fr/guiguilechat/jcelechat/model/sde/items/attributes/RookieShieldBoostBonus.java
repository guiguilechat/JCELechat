package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Bonus to shield booster repair amount
 */
public class RookieShieldBoostBonus
    extends DoubleAttribute
{
    public final static RookieShieldBoostBonus INSTANCE = new RookieShieldBoostBonus();

    @Override
    public int getId() {
        return  1837;
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
        return "RookieShieldBoostBonus";
    }
}
