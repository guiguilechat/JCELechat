package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorEnergyNeutralizerDuration
    extends IntAttribute
{
    public static final BehaviorEnergyNeutralizerDuration INSTANCE = new BehaviorEnergyNeutralizerDuration();

    @Override
    public int getId() {
        return  2519;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BehaviorEnergyNeutralizerDuration";
    }
}
