package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increase in Light Missile velocity
 */
public class RookieLightMissileVelocity
    extends IntAttribute
{
    public static final RookieLightMissileVelocity INSTANCE = new RookieLightMissileVelocity();

    @Override
    public int getId() {
        return  1862;
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
        return "RookieLightMissileVelocity";
    }
}
