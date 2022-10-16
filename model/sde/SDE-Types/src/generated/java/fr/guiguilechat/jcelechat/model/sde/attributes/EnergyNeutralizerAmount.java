package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * An amount to modify the power of the target by.
 */
public class EnergyNeutralizerAmount
    extends RealAttribute
{
    public static final EnergyNeutralizerAmount INSTANCE = new EnergyNeutralizerAmount();

    @Override
    public int getId() {
        return  97;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EnergyNeutralizerAmount";
    }
}
