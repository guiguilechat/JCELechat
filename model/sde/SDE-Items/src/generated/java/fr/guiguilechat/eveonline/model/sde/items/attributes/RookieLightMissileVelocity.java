package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Increase in Light Missile velocity
 */
public class RookieLightMissileVelocity
    extends IntAttribute
{
    public final static RookieLightMissileVelocity INSTANCE = new RookieLightMissileVelocity();

    @Override
    public int getId() {
        return  1862;
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
        return "RookieLightMissileVelocity";
    }
}
