package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Reduction in energy turret capacitor use
 */
public class RookieSETCapBonus
    extends IntAttribute
{
    public final static RookieSETCapBonus INSTANCE = new RookieSETCapBonus();

    @Override
    public int getId() {
        return  1822;
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
        return "RookieSETCapBonus";
    }
}
