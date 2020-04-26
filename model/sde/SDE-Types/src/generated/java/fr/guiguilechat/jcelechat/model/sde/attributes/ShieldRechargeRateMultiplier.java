package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to a recharge rate time.
 */
public class ShieldRechargeRateMultiplier
    extends DoubleAttribute
{
    public static final ShieldRechargeRateMultiplier INSTANCE = new ShieldRechargeRateMultiplier();

    @Override
    public int getId() {
        return  134;
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
        return "ShieldRechargeRateMultiplier";
    }
}
