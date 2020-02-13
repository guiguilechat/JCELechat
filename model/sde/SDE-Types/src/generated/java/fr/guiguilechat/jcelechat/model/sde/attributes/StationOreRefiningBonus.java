package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus for refining ore. Used for station improvements
 */
public class StationOreRefiningBonus
    extends DoubleAttribute
{
    public static final StationOreRefiningBonus INSTANCE = new StationOreRefiningBonus();

    @Override
    public int getId() {
        return  1939;
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
