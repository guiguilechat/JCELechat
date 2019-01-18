package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Capacity of Quafe hold
 */
public class SpecialQuafeHoldCapacity
    extends IntAttribute
{
    public static final SpecialQuafeHoldCapacity INSTANCE = new SpecialQuafeHoldCapacity();

    @Override
    public int getId() {
        return  1804;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SpecialQuafeHoldCapacity";
    }
}
