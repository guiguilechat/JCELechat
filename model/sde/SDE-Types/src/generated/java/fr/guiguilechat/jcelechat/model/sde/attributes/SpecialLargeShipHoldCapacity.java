package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of battleship hold
 */
public class SpecialLargeShipHoldCapacity
    extends IntAttribute
{
    public static final SpecialLargeShipHoldCapacity INSTANCE = new SpecialLargeShipHoldCapacity();

    @Override
    public int getId() {
        return  1563;
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
        return "SpecialLargeShipHoldCapacity";
    }
}
