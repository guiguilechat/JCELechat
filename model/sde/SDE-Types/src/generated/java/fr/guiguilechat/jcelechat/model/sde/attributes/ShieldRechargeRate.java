package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Amount of time taken to fully recharge the shield.
 */
public class ShieldRechargeRate
    extends RealAttribute
{
    public static final ShieldRechargeRate INSTANCE = new ShieldRechargeRate();

    @Override
    public int getId() {
        return  479;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "ShieldRechargeRate";
    }
}
