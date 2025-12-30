package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Increase in small energy turret tracking
 */
public class RookieSETTracking
    extends RealAttribute
{
    public static final RookieSETTracking INSTANCE = new RookieSETTracking();

    @Override
    public int getId() {
        return  1857;
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
        return "RookieSETTracking";
    }
}
