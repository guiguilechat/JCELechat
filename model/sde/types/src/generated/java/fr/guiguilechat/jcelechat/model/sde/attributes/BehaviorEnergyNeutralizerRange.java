package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class BehaviorEnergyNeutralizerRange
    extends RealAttribute
{
    public static final BehaviorEnergyNeutralizerRange INSTANCE = new BehaviorEnergyNeutralizerRange();

    @Override
    public int getId() {
        return  2520;
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
        return "BehaviorEnergyNeutralizerRange";
    }
}
