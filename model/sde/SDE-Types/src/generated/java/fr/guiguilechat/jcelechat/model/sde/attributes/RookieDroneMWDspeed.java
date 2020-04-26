package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increase in Drone MWD speed
 */
public class RookieDroneMWDspeed
    extends IntAttribute
{
    public static final RookieDroneMWDspeed INSTANCE = new RookieDroneMWDspeed();

    @Override
    public int getId() {
        return  1864;
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
        return "RookieDroneMWDspeed";
    }
}
