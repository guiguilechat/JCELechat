package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Capacity of Planetary Commodities hold
 */
public class SpecialPlanetaryCommoditiesHoldCapacity
    extends IntAttribute
{
    public final static SpecialPlanetaryCommoditiesHoldCapacity INSTANCE = new SpecialPlanetaryCommoditiesHoldCapacity();

    @Override
    public int getId() {
        return  1653;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "SpecialPlanetaryCommoditiesHoldCapacity";
    }
}
