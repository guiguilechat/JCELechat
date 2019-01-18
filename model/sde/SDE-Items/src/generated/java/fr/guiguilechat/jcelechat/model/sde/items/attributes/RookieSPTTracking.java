package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Increase in Small Projectile Turret tracking
 */
public class RookieSPTTracking
    extends DoubleAttribute
{
    public static final RookieSPTTracking INSTANCE = new RookieSPTTracking();

    @Override
    public int getId() {
        return  1867;
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
        return "RookieSPTTracking";
    }
}
