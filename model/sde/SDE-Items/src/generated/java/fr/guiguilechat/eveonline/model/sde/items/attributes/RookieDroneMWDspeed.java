package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Increase in Drone MWD speed
 */
public class RookieDroneMWDspeed
    extends IntAttribute
{
    public final static RookieDroneMWDspeed INSTANCE = new RookieDroneMWDspeed();

    @Override
    public int getId() {
        return  1864;
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
        return "RookieDroneMWDspeed";
    }
}
