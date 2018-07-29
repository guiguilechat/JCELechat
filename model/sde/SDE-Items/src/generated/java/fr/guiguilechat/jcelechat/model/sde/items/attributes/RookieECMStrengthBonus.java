package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * ECM Strength Bonus
 */
public class RookieECMStrengthBonus
    extends IntAttribute
{
    public final static RookieECMStrengthBonus INSTANCE = new RookieECMStrengthBonus();

    @Override
    public int getId() {
        return  1828;
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
        return "RookieECMStrengthBonus";
    }
}
