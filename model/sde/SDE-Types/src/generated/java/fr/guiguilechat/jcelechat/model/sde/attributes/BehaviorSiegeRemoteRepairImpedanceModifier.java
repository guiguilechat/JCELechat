package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class BehaviorSiegeRemoteRepairImpedanceModifier
    extends DoubleAttribute
{
    public static final BehaviorSiegeRemoteRepairImpedanceModifier INSTANCE = new BehaviorSiegeRemoteRepairImpedanceModifier();

    @Override
    public int getId() {
        return  2638;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "BehaviorSiegeRemoteRepairImpedanceModifier";
    }
}
