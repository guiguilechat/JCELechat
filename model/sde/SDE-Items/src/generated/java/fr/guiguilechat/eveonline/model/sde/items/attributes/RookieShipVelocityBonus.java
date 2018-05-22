package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to ship velocity
 */
public class RookieShipVelocityBonus
    extends IntAttribute
{
    public final static RookieShipVelocityBonus INSTANCE = new RookieShipVelocityBonus();

    @Override
    public int getId() {
        return  1835;
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
        return "RookieShipVelocityBonus";
    }
}
