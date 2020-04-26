package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SiegeRemoteLogisticsRangeBonus
    extends IntAttribute
{
    public static final SiegeRemoteLogisticsRangeBonus INSTANCE = new SiegeRemoteLogisticsRangeBonus();

    @Override
    public int getId() {
        return  2348;
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
        return "SiegeRemoteLogisticsRangeBonus";
    }
}
