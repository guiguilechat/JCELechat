package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorEnergyNeutralizerFalloff
    extends IntAttribute
{
    public static final BehaviorEnergyNeutralizerFalloff INSTANCE = new BehaviorEnergyNeutralizerFalloff();

    @Override
    public int getId() {
        return  2521;
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
        return "BehaviorEnergyNeutralizerFalloff";
    }
}
