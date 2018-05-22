package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplier to the capacitors recharge rate.
 */
public class CapacitorRechargeRateMultiplier
    extends DoubleAttribute
{
    public final static CapacitorRechargeRateMultiplier INSTANCE = new CapacitorRechargeRateMultiplier();

    @Override
    public int getId() {
        return  144;
    }

    @Override
    public int getCatId() {
        return  5;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "CapacitorRechargeRateMultiplier";
    }
}
