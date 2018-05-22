package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Amount of time taken to fully recharge the capacitor.
 */
public class RechargeRate
    extends DoubleAttribute
{
    public final static RechargeRate INSTANCE = new RechargeRate();

    @Override
    public int getId() {
        return  55;
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
        return "RechargeRate";
    }
}
