package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of Planetary Commodities hold
 */
public class SpecialPlanetaryCommoditiesHoldCapacity
    extends IntAttribute
{
    public static final SpecialPlanetaryCommoditiesHoldCapacity INSTANCE = new SpecialPlanetaryCommoditiesHoldCapacity();

    @Override
    public int getId() {
        return  1653;
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
        return "SpecialPlanetaryCommoditiesHoldCapacity";
    }
}
