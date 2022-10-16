package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of industrial ship hold
 */
public class SpecialIndustrialShipHoldCapacity
    extends IntAttribute
{
    public static final SpecialIndustrialShipHoldCapacity INSTANCE = new SpecialIndustrialShipHoldCapacity();

    @Override
    public int getId() {
        return  1564;
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
        return "SpecialIndustrialShipHoldCapacity";
    }
}
