package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorEnergyNeutralizerDischarge
    extends RealAttribute
{
    public static final BehaviorEnergyNeutralizerDischarge INSTANCE = new BehaviorEnergyNeutralizerDischarge();

    @Override
    public int getId() {
        return  2522;
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
        return "BehaviorEnergyNeutralizerDischarge";
    }
}
