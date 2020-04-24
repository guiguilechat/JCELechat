package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of frigate/destroyer hold
 */
public class SpecialSmallShipHoldCapacity
    extends IntAttribute
{
    public static final SpecialSmallShipHoldCapacity INSTANCE = new SpecialSmallShipHoldCapacity();

    @Override
    public int getId() {
        return  1561;
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
        return "SpecialSmallShipHoldCapacity";
    }
}
