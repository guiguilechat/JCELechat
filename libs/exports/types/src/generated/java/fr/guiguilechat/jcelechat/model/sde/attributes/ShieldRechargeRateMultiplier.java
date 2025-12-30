package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to a recharge rate time.
 */
public class ShieldRechargeRateMultiplier
    extends RealAttribute
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
        return "ShieldRechargeRateMultiplier";
    }
}
