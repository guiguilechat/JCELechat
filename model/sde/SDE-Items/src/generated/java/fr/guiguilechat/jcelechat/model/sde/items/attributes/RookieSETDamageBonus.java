package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Energy turret damage bonus
 */
public class RookieSETDamageBonus
    extends IntAttribute
{
    public static final RookieSETDamageBonus INSTANCE = new RookieSETDamageBonus();

    @Override
    public int getId() {
        return  1823;
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
        return "RookieSETDamageBonus";
    }
}
