package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Capacity of battleship hold
 */
public class SpecialLargeShipHoldCapacity
    extends IntAttribute
{
    public final static SpecialLargeShipHoldCapacity INSTANCE = new SpecialLargeShipHoldCapacity();

    @Override
    public int getId() {
        return  1563;
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
        return "SpecialLargeShipHoldCapacity";
    }
}
