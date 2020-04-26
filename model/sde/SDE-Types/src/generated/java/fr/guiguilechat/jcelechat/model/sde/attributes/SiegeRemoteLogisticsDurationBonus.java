package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
