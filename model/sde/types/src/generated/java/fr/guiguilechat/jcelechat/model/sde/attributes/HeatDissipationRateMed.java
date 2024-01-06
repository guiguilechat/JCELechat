package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class HeatDissipationRateMed
    extends RealAttribute
{
    public static final HeatDissipationRateMed INSTANCE = new HeatDissipationRateMed();

    @Override
    public int getId() {
        return  1196;
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
        return "HeatDissipationRateMed";
    }
}
