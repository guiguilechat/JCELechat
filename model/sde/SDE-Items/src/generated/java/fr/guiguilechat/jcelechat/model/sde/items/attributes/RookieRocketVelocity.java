package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Increase in Rocket velocity
 */
public class RookieRocketVelocity
    extends IntAttribute
{
    public final static RookieRocketVelocity INSTANCE = new RookieRocketVelocity();

    @Override
    public int getId() {
        return  1863;
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
        return "RookieRocketVelocity";
    }
}
