package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplier to a recharge rate time.
 */
public class ShieldRechargeRateMultiplier
    extends DoubleAttribute
{
    public final static ShieldRechargeRateMultiplier INSTANCE = new ShieldRechargeRateMultiplier();

    @Override
    public int getId() {
        return  134;
    }

    @Override
    public int getCatId() {
        return  2;
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
}
