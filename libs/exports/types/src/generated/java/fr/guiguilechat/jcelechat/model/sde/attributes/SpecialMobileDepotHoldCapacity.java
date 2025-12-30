package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Mobile Depot Hold Capacity
 */
public class SpecialMobileDepotHoldCapacity
    extends IntAttribute
{
    public static final SpecialMobileDepotHoldCapacity INSTANCE = new SpecialMobileDepotHoldCapacity();

    @Override
    public int getId() {
        return  5325;
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
        return "SpecialMobileDepotHoldCapacity";
    }
}
