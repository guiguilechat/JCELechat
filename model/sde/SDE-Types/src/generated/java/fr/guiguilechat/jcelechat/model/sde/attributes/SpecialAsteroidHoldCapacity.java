package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of asteroid-only hold
 */
public class SpecialAsteroidHoldCapacity
    extends IntAttribute
{
    public static final SpecialAsteroidHoldCapacity INSTANCE = new SpecialAsteroidHoldCapacity();

    @Override
    public int getId() {
        return  3227;
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
        return "SpecialAsteroidHoldCapacity";
    }
}
