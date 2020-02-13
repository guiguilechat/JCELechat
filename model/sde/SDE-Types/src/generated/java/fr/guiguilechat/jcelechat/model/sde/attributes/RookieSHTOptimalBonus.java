package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Small Hybrid Turret optimal range bonus
 */
public class RookieSHTOptimalBonus
    extends IntAttribute
{
    public static final RookieSHTOptimalBonus INSTANCE = new RookieSHTOptimalBonus();

    @Override
    public int getId() {
        return  1826;
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
        return "RookieSHTOptimalBonus";
    }
}
