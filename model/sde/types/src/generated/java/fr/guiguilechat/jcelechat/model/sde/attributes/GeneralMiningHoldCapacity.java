package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of general mining hold
 */
public class GeneralMiningHoldCapacity
    extends IntAttribute
{
    public static final GeneralMiningHoldCapacity INSTANCE = new GeneralMiningHoldCapacity();

    @Override
    public int getId() {
        return  1556;
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
        return "GeneralMiningHoldCapacity";
    }
}
