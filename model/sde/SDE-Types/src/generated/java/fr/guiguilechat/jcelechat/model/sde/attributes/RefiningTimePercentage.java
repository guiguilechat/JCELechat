package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Refining speed percentage
 */
public class RefiningTimePercentage
    extends IntAttribute
{
    public static final RefiningTimePercentage INSTANCE = new RefiningTimePercentage();

    @Override
    public int getId() {
        return  368;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  100.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RefiningTimePercentage";
    }
}
