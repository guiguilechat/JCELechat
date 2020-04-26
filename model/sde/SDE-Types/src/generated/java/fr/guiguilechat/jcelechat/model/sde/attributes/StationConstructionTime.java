package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Station construction time
 */
public class StationConstructionTime
    extends IntAttribute
{
    public static final StationConstructionTime INSTANCE = new StationConstructionTime();

    @Override
    public int getId() {
        return  395;
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
        return "StationConstructionTime";
    }
}
