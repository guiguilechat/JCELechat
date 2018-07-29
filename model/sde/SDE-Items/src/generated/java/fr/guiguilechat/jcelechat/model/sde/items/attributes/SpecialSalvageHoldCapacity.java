package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Capacity of special salvage-only hold
 */
public class SpecialSalvageHoldCapacity
    extends IntAttribute
{
    public final static SpecialSalvageHoldCapacity INSTANCE = new SpecialSalvageHoldCapacity();

    @Override
    public int getId() {
        return  1559;
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
        return "SpecialSalvageHoldCapacity";
    }
}
