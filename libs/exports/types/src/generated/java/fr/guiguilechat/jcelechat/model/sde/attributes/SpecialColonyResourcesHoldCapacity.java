package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Infrastructure Hold Capacity
 */
public class SpecialColonyResourcesHoldCapacity
    extends IntAttribute
{
    public static final SpecialColonyResourcesHoldCapacity INSTANCE = new SpecialColonyResourcesHoldCapacity();

    @Override
    public int getId() {
        return  5646;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpecialColonyResourcesHoldCapacity";
    }
}
