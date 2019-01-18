package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class IndustrialCoreRemoteLogisticsDurationBonus
    extends IntAttribute
{
    public static final IndustrialCoreRemoteLogisticsDurationBonus INSTANCE = new IndustrialCoreRemoteLogisticsDurationBonus();

    @Override
    public int getId() {
        return  2605;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "IndustrialCoreRemoteLogisticsDurationBonus";
    }
}
