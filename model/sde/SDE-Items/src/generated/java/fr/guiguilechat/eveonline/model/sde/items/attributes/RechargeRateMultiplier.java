package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Capacitor recharge rate multiplier
 */
public class RechargeRateMultiplier
    extends DoubleAttribute
{
    public final static RechargeRateMultiplier INSTANCE = new RechargeRateMultiplier();

    @Override
    public int getId() {
        return  1500;
    }

    @Override
    public int getCatId() {
        return  5;
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
        return "RechargeRateMultiplier";
    }
}
