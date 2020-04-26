package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to ship velocity
 */
public class RookieShipVelocityBonus
    extends IntAttribute
{
    public static final RookieShipVelocityBonus INSTANCE = new RookieShipVelocityBonus();

    @Override
    public int getId() {
        return  1835;
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
