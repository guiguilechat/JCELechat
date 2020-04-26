package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduction of power grid needs of energy transfer arrays
 */
public class PowerTransferPowerNeedBonus
    extends IntAttribute
{
    public static final PowerTransferPowerNeedBonus INSTANCE = new PowerTransferPowerNeedBonus();

    @Override
    public int getId() {
        return  1218;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PowerTransferPowerNeedBonus";
    }
}
