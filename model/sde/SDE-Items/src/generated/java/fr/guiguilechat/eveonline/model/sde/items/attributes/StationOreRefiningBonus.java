package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus for refining ore. Used for station improvements
 */
public class StationOreRefiningBonus
    extends DoubleAttribute
{
    public final static StationOreRefiningBonus INSTANCE = new StationOreRefiningBonus();

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
