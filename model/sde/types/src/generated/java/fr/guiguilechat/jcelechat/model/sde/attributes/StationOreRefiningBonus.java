package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus for refining ore. Used for station improvements
 */
public class StationOreRefiningBonus
    extends RealAttribute
{
    public static final StationOreRefiningBonus INSTANCE = new StationOreRefiningBonus();

    @Override
    public int getId() {
        return  1939;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StationOreRefiningBonus";
    }
}
