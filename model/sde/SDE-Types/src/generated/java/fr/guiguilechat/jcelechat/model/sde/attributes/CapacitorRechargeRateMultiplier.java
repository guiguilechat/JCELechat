package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the capacitors recharge rate.
 */
public class CapacitorRechargeRateMultiplier
    extends RealAttribute
{
    public static final CapacitorRechargeRateMultiplier INSTANCE = new CapacitorRechargeRateMultiplier();

    @Override
    public int getId() {
        return  144;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
