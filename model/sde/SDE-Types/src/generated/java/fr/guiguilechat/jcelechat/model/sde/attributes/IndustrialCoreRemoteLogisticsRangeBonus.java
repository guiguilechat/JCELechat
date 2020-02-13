package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialCoreRemoteLogisticsRangeBonus
    extends IntAttribute
{
    public static final IndustrialCoreRemoteLogisticsRangeBonus INSTANCE = new IndustrialCoreRemoteLogisticsRangeBonus();

    @Override
    public int getId() {
        return  2604;
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
        return "IndustrialCoreRemoteLogisticsRangeBonus";
    }
}
