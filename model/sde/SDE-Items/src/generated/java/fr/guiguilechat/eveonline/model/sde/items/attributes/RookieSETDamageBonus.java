package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Energy turret damage bonus
 */
public class RookieSETDamageBonus
    extends IntAttribute
{
    public final static RookieSETDamageBonus INSTANCE = new RookieSETDamageBonus();

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
