package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class BehaviorSiegeRemoteCapacitorImpedanceModifier
    extends RealAttribute
{
    public static final BehaviorSiegeRemoteCapacitorImpedanceModifier INSTANCE = new BehaviorSiegeRemoteCapacitorImpedanceModifier();

    @Override
    public int getId() {
        return  6465;
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
        return "BehaviorSiegeRemoteCapacitorImpedanceModifier";
    }
}
