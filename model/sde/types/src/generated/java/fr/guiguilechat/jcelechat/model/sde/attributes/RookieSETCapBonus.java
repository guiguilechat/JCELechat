package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduction in energy turret capacitor use
 */
public class RookieSETCapBonus
    extends IntAttribute
{
    public static final RookieSETCapBonus INSTANCE = new RookieSETCapBonus();

    @Override
    public int getId() {
        return  1822;
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
        return "RookieSETCapBonus";
    }
}
