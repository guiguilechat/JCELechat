package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SiegeRemoteLogisticsAmountBonus
    extends IntAttribute
{
    public static final SiegeRemoteLogisticsAmountBonus INSTANCE = new SiegeRemoteLogisticsAmountBonus();

    @Override
    public int getId() {
        return  2345;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "SiegeRemoteLogisticsAmountBonus";
    }
}
