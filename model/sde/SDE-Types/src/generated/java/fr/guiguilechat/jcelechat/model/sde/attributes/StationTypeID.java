package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The type of station this platform can be used to build.
 */
public class StationTypeID
    extends IntAttribute
{
    public static final StationTypeID INSTANCE = new StationTypeID();

    @Override
    public int getId() {
        return  472;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StationTypeID";
    }
}
