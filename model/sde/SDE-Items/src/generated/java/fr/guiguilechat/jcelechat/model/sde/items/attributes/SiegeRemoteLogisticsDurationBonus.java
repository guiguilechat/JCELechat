package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class SiegeRemoteLogisticsDurationBonus
    extends IntAttribute
{
    public static final SiegeRemoteLogisticsDurationBonus INSTANCE = new SiegeRemoteLogisticsDurationBonus();

    @Override
    public int getId() {
        return  2344;
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
        return "SiegeRemoteLogisticsDurationBonus";
    }
}
