package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class WarpAccuracyMaxRange
    extends IntAttribute
{
    public static final WarpAccuracyMaxRange INSTANCE = new WarpAccuracyMaxRange();

    @Override
    public int getId() {
        return  1021;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  15000.0;
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
        return "WarpAccuracyMaxRange";
    }
}
