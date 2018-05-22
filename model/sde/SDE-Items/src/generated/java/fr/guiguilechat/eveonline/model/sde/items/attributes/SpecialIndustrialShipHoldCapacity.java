package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Capacity of industrial ship hold
 */
public class SpecialIndustrialShipHoldCapacity
    extends IntAttribute
{
    public final static SpecialIndustrialShipHoldCapacity INSTANCE = new SpecialIndustrialShipHoldCapacity();

    @Override
    public int getId() {
        return  1564;
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
        return "SpecialIndustrialShipHoldCapacity";
    }
}
