package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Capacity of ship-only hold
 */
public class SpecialShipHoldCapacity
    extends IntAttribute
{
    public final static SpecialShipHoldCapacity INSTANCE = new SpecialShipHoldCapacity();

    @Override
    public int getId() {
        return  1560;
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
        return "SpecialShipHoldCapacity";
    }
}
